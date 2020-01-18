package com.longyx.product.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mr.Longyx
 * @date 2020年01月15日 17:33
 */
@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_info")
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = -3477692005251954306L;
    /**商品id*/
    @Id
    private String productId;
    /**名字*/
    private String productName;
    /**商品价格*/
    private BigDecimal productPrice;
    /**商品库存*/
    private Integer productStock;
    /**商品描述*/
    private String productDescription;
    /**商品图标*/
    private String productIcon;
    /**商品状态*/
    private Integer productStatus;
    /**类目编号*/
    private Integer categoryType;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
}
