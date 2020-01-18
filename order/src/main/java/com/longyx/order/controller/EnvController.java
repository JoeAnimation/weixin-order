package com.longyx.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 11:32
 */
@RestController
@RequestMapping("/env")
@RefreshScope
public class EnvController{
    @Value("${env}")
    private String env;

    @RequestMapping("/print")
    public String print(){
        return env;
    }
}
