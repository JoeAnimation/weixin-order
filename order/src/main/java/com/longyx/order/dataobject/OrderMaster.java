package com.longyx.order.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:23
 */
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@TableName("order_master")
@ToString
public class OrderMaster implements Serializable {
    private static final long serialVersionUID = -4085509151274523546L;
    /**订单id*/
    @Id
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

    /**支付状态*/
    private Integer payStatus;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;
}

