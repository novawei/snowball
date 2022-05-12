package com.nova.common.web.annotation;

import com.nova.common.web.exception.CustomErrorAttributes;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {CustomErrorAttributes.class})
public @interface EnableGlobalExceptionAdvice {
}
