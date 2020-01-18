package com.longyx.product.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 * @author Mr.Longyx
 * @date 2020年01月11日 21:35
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 5730018187035344854L;
    /**
     * 错误码
     * @author Mr.Longyx
     * @date 2020/1/11 21:35
     */
    private Integer code;

    /**
     * 提示信息
     * @author Mr.Longyx
     * @date 2020/1/11 21:36
     */
    private String msg;

    /**
     * 具体内容
     * @author Mr.Longyx
     * @date 2020/1/11 21:36
     */
    private T data;
}
