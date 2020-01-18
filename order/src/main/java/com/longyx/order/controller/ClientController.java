package com.longyx.order.controller;

import com.longyx.product.client.ProductClient;
import com.longyx.product.common.DecreaseStockInput;
import com.longyx.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:16
 */
@RestController
@Slf4j
public class ClientController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;
    /**
     * 第一种方式(直接使用restTemplate，url固定)
     * @author Mr.Longyx
     * @date 2020/1/12 9:30
     * @return java.lang.String
     */
    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:9999/msg", String.class);
        log.info("response={}", response);

        return response;
    }

    /**
     * 第二种方式(利用loadBalanceClient,通过应用名获取url，然后再使用restTemplate）
     * @author Mr.Longyx
     * @date 2020/1/12 9:30
     */
    @GetMapping("/getProductMessage" )
    public String getProductMessage(){
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s",serviceInstance.getHost(), serviceInstance.getPort())+"/msg";
        String response = restTemplate.getForObject(url, String.class);

        log.info("response={}", response);

        return response;
    }
    /**
     * 第三种方式(利用@LoadBalanced,可在restTemplate里使用应用名字)
     * @author Mr.Longyx
     * @date 2020/1/12 9:44
     */
    @GetMapping("/getProductMess")
    public String getProductMess(){
        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);

        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProdMsg" )
    public String getProdMsg(){
        String response = productClient.productMsg();
        log.info("response={}", response);

        return response;
    }

    @GetMapping("/getProductList" )
    public String getProductList(){
        List<ProductInfoOutput> productInfoList =  productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response={}", productInfoList);

        return "ok";
    }
    /**
     * 扣库存
     * @author Mr.Longyx
     * @date 2020/1/12 12:38
     * @return java.lang.String
     */
    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("164103465734242707", 5)));
        return "ok";
    }
}


