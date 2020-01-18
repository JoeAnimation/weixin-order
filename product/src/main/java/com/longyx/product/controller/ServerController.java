package com.longyx.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Longyx
 * @date 2020年01月12日 9:16
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg(){
        return "this is the product's msg";
    }
}
