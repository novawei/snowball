package com.nova.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.nova.gateway.filter.XOriginFilter;
import com.nova.gateway.handler.JsonBlockRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class GatewayConfiguration {
    @Bean
    public XOriginFilter xOriginFilter() {
        return new XOriginFilter();
    }

    @PostConstruct
    public void initBlockHandler() {
        GatewayCallbackManager.setBlockHandler(new JsonBlockRequestHandler());
    }
}
