package com.longyx.product.enums;

import lombok.Getter;

/**
 * @author Mr.Longyx
 * @date 2020年01月12日 12:07
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存异常"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String  message){
        this.code = code;
        this.message = message;
    }
}
