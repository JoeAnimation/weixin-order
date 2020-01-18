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

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_detail")
@ToString
@Entity
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = -3783863257039905656L;

    /**详情id*/
    @Id
    private String detailId;

    /**订单id*/
    private String orderId;

    /**商品id*/
    private String productId;

    /**商品名称*/
    private String productName;

    /**商品单价*/
    private BigDecimal productPrice;

    /**商品数量*/
    private Integer productQuantity;

    /**商品小图*/
    private String productIcon;

}

