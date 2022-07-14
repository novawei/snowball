package com.nova.common.web.annotation;

import com.nova.common.web.exception.DefaultServletErrorAttributes;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {DefaultServletErrorAttributes.class})
public @interface EnableServletExceptionAdvice {
}
