package com.longyx.product.repository;

import com.longyx.product.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月15日 21:58
 */
@SpringBootTest
class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void findByCategoryType() {
        List<ProductCategory> productCategories =  productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11,22));
        Assert.assertTrue(productCategories.size()>0);
    }
}
