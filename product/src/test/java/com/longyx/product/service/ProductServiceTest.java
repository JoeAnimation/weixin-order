package com.longyx.product.service;

import com.longyx.product.common.DecreaseStockInput;
import com.longyx.product.common.ProductInfoOutput;
import com.longyx.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月15日 22:01
 */
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void findUpAll() {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertTrue(productInfos.size()>0);
    }

    @Test
    void findList(){
        List<ProductInfoOutput> productInfoOutputs = productService.findList(Arrays.asList("157875227953464068", "164103465734242707"));
        Assert.assertTrue(productInfoOutputs.size()>0);
    }

    @Test
    void decreaseStock() throws Exception{
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("157875227953464068", 2);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }
}
