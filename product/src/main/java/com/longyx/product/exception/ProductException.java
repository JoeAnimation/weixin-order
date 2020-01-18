package com.longyx.product.exception;

import com.longyx.product.enums.ResultEnum;

/**
 * @author Mr.Longyx
 * @date 2020年01月12日 12:04
 */
public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum){
        super(resultEnum.getMessage().trim());
        this.code = resultEnum.getCode();
    }
}
