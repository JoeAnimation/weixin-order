package com.longyx.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.longyx.zuul.exception.RateLimiterException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流
 * @author Mr.Longyx
 * @date 2020年01月17日 9:10
 */
@Component
public class RateLimiterFilter extends ZuulFilter {

    private static  final RateLimiter rateLimiter = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (!rateLimiter.tryAcquire()) {
            throw new RateLimiterException();
        }
        return null;
    }
}
