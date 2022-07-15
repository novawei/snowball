package com.nova.service.auth.service;

import com.nova.common.security.annotation.EnableWebSecurity;
import com.nova.common.web.annotation.EnableApiFeignClients;
import com.nova.common.web.annotation.EnableServletExceptionAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableWebSecurity
@EnableDiscoveryClient
@EnableApiFeignClients
@EnableServletExceptionAdvice
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
