package com.longyx.order.service;

import com.longyx.order.dto.OrderDTO;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:31
 */
public interface OrderService {
    /**
     * 创建订单
     * @author Mr.Longyx
     * @date 2020/1/12 0:51
     * @param orderDTO
     * @return com.longyx.order.dto.OrderDTO
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单(只能卖家来操作)
     * @author Mr.Longyx
     * @date 2020/1/16 23:36
     * @param orderId
     * @return com.longyx.order.dto.OrderDTO
     */
    OrderDTO finish(String orderId);
}

