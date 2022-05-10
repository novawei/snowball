package com.nova.common.web.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients(
        basePackages = {"com.nova.service.**.api.client"}
)
public @interface EnableApiFeignClients {
    @AliasFor(
            annotation = EnableFeignClients.class
    )
    String[] value() default {};

    @AliasFor(
            annotation = EnableFeignClients.class
    )
    Class<?>[] basePackageClasses() default {};

    @AliasFor(
            annotation = EnableFeignClients.class
    )
    Class<?>[] defaultConfiguration() default {};

    @AliasFor(
            annotation = EnableFeignClients.class
    )
    Class<?>[] clients() default {};
}
