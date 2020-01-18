package com.longyx.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.longyx.order.dataobject.OrderDetail;
import com.longyx.order.dto.OrderDTO;
import com.longyx.order.enums.ResultEnum;
import com.longyx.order.exception.OrderException;
import com.longyx.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:20
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){

            }.getType());
        }catch (Exception e){
            log.error(" 【json转换】 错误， string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}

