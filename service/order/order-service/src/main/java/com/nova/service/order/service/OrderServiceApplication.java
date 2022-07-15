package com.nova.service.order.service;

import com.nova.common.security.annotation.EnableWebSecurity;
import com.nova.common.web.annotation.EnableApiFeignClients;
import com.nova.common.web.annotation.EnableServletExceptionAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableWebSecurity
@EnableDiscoveryClient
@EnableApiFeignClients
@EnableServletExceptionAdvice
@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
