package com.longyx.product.service.impl;

import com.longyx.product.common.DecreaseStockInput;
import com.longyx.product.common.ProductInfoOutput;
import com.longyx.product.dataobject.ProductInfo;
import com.longyx.product.enums.ProductStatusEnum;
import com.longyx.product.enums.ResultEnum;
import com.longyx.product.exception.ProductException;
import com.longyx.product.repository.ProductInfoRepository;
import com.longyx.product.service.ProductService;
import com.longyx.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mr.Longyx
 * @date 2020年01月11日 21:16
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream().map(e->{
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
    }
    
    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockHandler(decreaseStockInputList);

        //发送mq消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e->{
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    /**
     * 具体扣减库存的方法体
     * @author Mr.Longyx
     * @date 2020/1/15 16:35
     * @param decreaseStockInputList
     * @return java.util.List<com.longyx.product.dataobject.ProductInfo>
     */
    @Transactional
    public List<ProductInfo> decreaseStockHandler(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfos = new ArrayList<>();
        for(DecreaseStockInput decreaseStockInput : decreaseStockInputList){
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock()-decreaseStockInput.getProductQuantity();
            //判断库存是否足够
            if(result < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

            productInfos.add(productInfo);
        }
        return  productInfos;
    }
}
