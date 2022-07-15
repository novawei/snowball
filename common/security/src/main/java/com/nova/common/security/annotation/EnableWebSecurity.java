package com.nova.common.security.annotation;

import com.nova.common.security.config.WebSecurityConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {WebSecurityConfiguration.class})
public @interface EnableWebSecurity {
}
