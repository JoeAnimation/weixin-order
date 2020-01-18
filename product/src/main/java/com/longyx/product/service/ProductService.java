package com.longyx.product.service;

import com.longyx.product.common.DecreaseStockInput;
import com.longyx.product.common.ProductInfoOutput;
import com.longyx.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月11日 21:13
 */
public interface ProductService {
    /**
     * 查询所有在架的商品列表
     * @author Mr.Longyx
     * @date 2020/1/11 21:14
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @author Mr.Longyx
     * @date 2020/1/12 11:12
     * @param productIdList
     * @return java.util.List<com.longyx.product.dataobject.ProductInfo>
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣减库存
     * @author Mr.Longyx
     * @date 2020/1/12 12:00
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
