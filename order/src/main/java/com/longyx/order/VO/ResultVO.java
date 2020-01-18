package com.longyx.order.VO;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:35
 */
@Data
@ToString
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -1412393268780221328L;

    private Integer code;

    private String msg;

    private  T data;
}
