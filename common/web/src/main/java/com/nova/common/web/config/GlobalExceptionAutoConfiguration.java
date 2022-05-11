package com.nova.common.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {"com.nova.common.web.exception"})
public class GlobalExceptionAutoConfiguration {
}
