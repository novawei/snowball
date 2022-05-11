package com.nova.common.web.annotation;

import com.nova.common.web.config.GlobalExceptionAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {GlobalExceptionAutoConfiguration.class})
public @interface EnableGlobalExceptionAdvice {
}
