package com.example.gatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class PreFilter extends ZuulFilter {
    private static Logger log= LoggerFactory.getLogger(PreFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override // define the priority of the filterType
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override // The action, anything inside run will execute
    public Object run() throws ZuulException {
        RequestContext ctx =RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // Add a custom header in the request
        ctx.addZuulRequestHeader("Authorization", request.getHeader("Authorization"));
        log.info("Request method={}, url={}", request.getMethod(),request.getRequestURL().toString());
        return null;
    }
}
