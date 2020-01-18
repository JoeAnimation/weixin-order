package com.longyx.product.common;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Mr.Longyx
 * @date 2020年01月15日 17:25
 */
@Data
public class ProductInfoOutput implements Serializable {
    private static final long serialVersionUID = -3791688602564406980L;
    /**商品id*/
    @Id
    private String productId;
    /**名字*/
    private String productName;
    /**商品价格*/
    private BigDecimal productPrice;
    /**商品库存*/
    private Integer productStock;
    /**商品描述*/
    private String productDescription;
    /**商品图标*/
    private String productIcon;
    /**商品状态*/
    private Integer productStatus;
    /**类目编号*/
    private Integer categoryType;
}

