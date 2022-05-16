package com.nova.common.log.config;

import com.nova.common.log.aspect.ApiLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiLogAutoConfiguration {
    @Bean
    public ApiLogAspect apiLogAspect() {
        return new ApiLogAspect();
    }
}
