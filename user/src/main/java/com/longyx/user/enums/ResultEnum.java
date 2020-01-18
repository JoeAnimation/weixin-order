package com.longyx.user.enums;

import lombok.Getter;

/**
 * @author Mr.Longyx
 * @date 2020年01月16日 21:48
 */
@Getter
public enum  ResultEnum {
    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(2, "角色权限有误"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
