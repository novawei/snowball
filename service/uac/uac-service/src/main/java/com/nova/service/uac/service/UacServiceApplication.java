package com.nova.service.uac.service;

import com.nova.common.security.annotation.EnableWebSecurity;
import com.nova.common.web.annotation.EnableServletExceptionAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableWebSecurity
@EnableDiscoveryClient
@EnableServletExceptionAdvice
@SpringBootApplication
public class UacServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UacServiceApplication.class, args);
    }
}
