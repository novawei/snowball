package com.nova.common.seata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:seata.properties")
@Configuration(proxyBeanMethods = false)
public class SeataAutoConfiguration {
}
