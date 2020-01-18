package com.longyx.product.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Mr.Longyx
 * @date 2020年01月15日 17:23
 */
@Data
@NoArgsConstructor
public class DecreaseStockInput implements Serializable {
    private static final long serialVersionUID = 3747166682741066833L;
    /**商品id*/
    private String productId;

    /**商品数量*/
    private Integer productQuantity;

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

