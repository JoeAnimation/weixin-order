package com.longyx.order.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:29
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm implements Serializable {
    private static final long serialVersionUID = -1767472378692253320L;
    /**
     * 买家姓名
     */
    @NotEmpty(message = "买家姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "买家手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "买家收货地址必填")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "买家openid必填")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}