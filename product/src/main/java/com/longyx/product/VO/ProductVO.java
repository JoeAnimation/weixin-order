package com.longyx.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月11日 21:37
 */
@Data
public class ProductVO implements Serializable {
    private static final long serialVersionUID = 7313019254435266619L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    List<ProductInfoVO> productInfoVOList;
}
