package com.longyx.order.service.impl;

import com.longyx.order.dataobject.OrderDetail;
import com.longyx.order.dataobject.OrderMaster;
import com.longyx.order.dto.OrderDTO;
import com.longyx.order.enums.OrderStatusEnum;
import com.longyx.order.enums.PayStatusEnum;
import com.longyx.order.enums.ResultEnum;
import com.longyx.order.exception.OrderException;
import com.longyx.order.repository.OrderDetailRepository;
import com.longyx.order.repository.OrderMasterRepository;
import com.longyx.order.service.OrderService;
import com.longyx.order.util.KeyGenUtil;
import com.longyx.product.client.ProductClient;
import com.longyx.product.common.DecreaseStockInput;
import com.longyx.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:32
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyGenUtil.genUniqueKey();
        // 查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        //计时：判断性能
        List<ProductInfoOutput> productInfos = productClient.listForOrder(productIdList);

        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            //总价=单价*数量
            for(ProductInfoOutput productInfo : productInfos){
                if(productInfo.getProductId().equals(orderDetail.getProductId())){
                    orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyGenUtil.genUniqueKey());

                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        //扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputs = orderDTO.getOrderDetailList().stream().map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productClient.decreaseStock(decreaseStockInputs);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        //1.查询订单
        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        if (!orderMaster.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXISTS);
        }
        //2.判断订单状态
       OrderMaster order =  orderMaster.get();
        if(!OrderStatusEnum.NEW.getCode().equals(order.getOrderStatus())){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3.修改订单状态为完结
        order.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(order);

        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}

