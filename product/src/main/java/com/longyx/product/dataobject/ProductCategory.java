package com.longyx.product.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Longyx
 * @date 2020年01月11日 19:45
 */
@Data
@Entity
@ToString
@TableName("product_category")
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = -5753414040458301333L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**类目名字*/
    private String categoryName;

    /**类目编号*/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
