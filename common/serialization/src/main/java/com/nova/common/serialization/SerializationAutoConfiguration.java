package com.nova.common.serialization;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:serialization.properties")
@Configuration(proxyBeanMethods = false)
public class SerializationAutoConfiguration {
}
