package com.longyx.order.exception;

import com.longyx.order.enums.ResultEnum;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:28
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
