package com.longyx.order.enums;

import lombok.Getter;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:26
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消订单"),
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
