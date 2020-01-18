package com.longyx.product.enums;

import lombok.Getter;

/**
 * 商品上下架状态
 * @author Mr.Longyx
 * @date 2020年01月11日 21:17
 */
@Getter
public enum  ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架"),
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
