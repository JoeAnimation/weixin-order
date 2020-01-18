package com.longyx.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.longyx.product.common.ProductInfoOutput;
import com.longyx.product.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月14日 12:04
 */
@Component
@Slf4j
public class ProductInfoReceiver {
    private static final String PRODUCT_STOCK_TEMPLATE="product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
        //message => productInfo
       List<ProductInfoOutput> productInfoOutputList = Collections.unmodifiableList((List<ProductInfoOutput>) JsonUtil.fromJson(message,
               new TypeReference<List<ProductInfoOutput>>() {}));
       log.info("从队列 【{}】 接收到消息: {}", "productInfo",productInfoOutputList);

       //存储到redis中
        assert productInfoOutputList != null;
        for(ProductInfoOutput productInfo : productInfoOutputList){
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()),String.valueOf(productInfo.getProductStock()));
        }
    }
}
