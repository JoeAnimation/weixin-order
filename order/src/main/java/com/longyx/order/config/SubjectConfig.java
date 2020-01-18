package com.longyx.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 19:49
 */
@Data
@Component
@ConfigurationProperties("subject")
@RefreshScope
public class SubjectConfig {
    private String name;
    private Integer score;
}
