package com.longyx.product.service;

import com.longyx.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月11日 21:25
 */
public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
