package com.nova.gateway.annotation;

import com.nova.gateway.exception.DefaultReactiveErrorAttributes;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {DefaultReactiveErrorAttributes.class})
public @interface EnableReactiveExceptionAdvice {
}
