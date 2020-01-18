package com.longyx.product.repository;

import com.longyx.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月15日 22:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    void findByProductStatus() {
        List<ProductInfo> productInfos = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(productInfos.size()>0);
    }

    @Test
    void findByProductIdIn() throws Exception{
        List<ProductInfo> productInfos = productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160022", "164103465734242707"));
        Assert.assertTrue(productInfos.size()>0);
    }
}