package com.nova.common.security.annotation;

import com.nova.common.security.config.WebSecurityConfiguration;
import com.nova.common.security.exception.SecurityExceptionAdvice;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {WebSecurityConfiguration.class, SecurityExceptionAdvice.class})
public @interface EnableWebSecurity {
}
