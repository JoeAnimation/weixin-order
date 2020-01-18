package com.longyx.order.controller;

import com.longyx.order.config.SubjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 19:52
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectConfig subjectConfig;

    @RequestMapping("/print" )
    public String print(){
        return "name: "+subjectConfig.getName()+" score: "+ subjectConfig.getScore();
    }
}
