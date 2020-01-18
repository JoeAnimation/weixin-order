package com.longyx.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Mr.Longyx
 * @date 2020年01月17日 22:06
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallBack")
public class HystrixController {
   final String HTTP_PREFIX = "http://localhost:9999";
   final String URL = "/product/listForOrder";

   //超时配置
   //@HystrixCommand(fallbackMethod = "fallback", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
   @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("code")Integer code){
        if(code % 2 == 0){
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(HTTP_PREFIX + URL, Arrays.asList("164103465734242707"), String.class);
    }

    private String fallback(){
       return "当前网络拥挤，请稍后重试~~~";
    }

    private String defaultFallBack(){
       return "默认提示：网络超时，请稍后重试~~";
    }
}
