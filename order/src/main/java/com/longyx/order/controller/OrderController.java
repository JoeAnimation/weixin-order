package com.longyx.order.controller;

import com.longyx.order.converter.OrderForm2OrderDTOConverter;
import com.longyx.order.dto.OrderDTO;
import com.longyx.order.enums.ResultEnum;
import com.longyx.order.exception.OrderException;
import com.longyx.order.form.OrderForm;
import com.longyx.order.service.OrderService;
import com.longyx.product.VO.ResultVO;
import com.longyx.product.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:18
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1.参数校验
     * 2.查询商品信息(调用商品服务)
     * 3.计算总价
     * 4.扣库存(调用商品服务)
     * 5.订单入库
     * @author Mr.Longyx
     * @date 2020/1/12 0:45
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVoUtil.success(map);
    }

    /**
     * 完结订单
     * @author Mr.Longyx
     * @date 2020/1/17 0:04
     * @param orderId
     * @return com.longyx.product.VO.ResultVO<com.longyx.order.dto.OrderDTO>
     */
    @PostMapping("/finish" )
    public ResultVO<OrderDTO> finish(@RequestParam("orderId")String orderId){
        return ResultVoUtil.success(orderService.finish(orderId));
    }
}
