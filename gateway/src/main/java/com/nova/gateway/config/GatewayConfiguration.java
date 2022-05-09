package com.nova.gateway.config;

import com.nova.gateway.filter.XOriginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    @Bean
    public XOriginFilter xOriginFilter() {
        return new XOriginFilter();
    }
}
