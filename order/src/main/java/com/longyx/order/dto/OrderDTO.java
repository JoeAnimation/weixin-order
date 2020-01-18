package com.longyx.order.dto;

import com.longyx.order.dataobject.OrderDetail;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:25
 */
@Data
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 911714864160371516L;
    /**订单id*/
    private String orderId;

    /**买家姓名*/
    private String buyerName;

    /**卖家手机号*/
    private String buyerPhone;

    /**买家地址*/
    private String buyerAddress;

    /**买家openid*/
    private String buyerOpenid;

    /**订单总金额*/
    private BigDecimal orderAmount;

    /**订单状态*/
    private Integer orderStatus;

    /**支付状态 默认为0未支付*/
    private Integer payStatus;

    private List<OrderDetail> orderDetailList;
}
