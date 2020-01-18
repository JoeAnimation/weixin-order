package com.longyx.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Mr.Longyx
 * @date 2020年01月11日 21:40
 */
@Data
public class ProductInfoVO implements Serializable {
    private static final long serialVersionUID = -1730819922473534497L;
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description" )
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
