package com.nova.common.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class ApiLogAspect {
    /**
     * Log annotation.
     */
    @Pointcut("@annotation(com.nova.common.log.annotation.ApiLog)")
    public void logAnnotation() {
    }

    /**
     * Do before.
     */
    @Before("logAnnotation()")
    public void doBefore() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("Api Request: Method={} Path={}", request.getMethod(), request.getRequestURI());
    }

    /**
     * Do after.
     *
     * @param joinPoint   the join point
     * @param returnValue the return value
     */
    @AfterReturning(pointcut = "logAnnotation()", returning = "returnValue")
    public void doAfter(final JoinPoint joinPoint, final Object returnValue) {
        String strClassName = joinPoint.getTarget().getClass().getName();
        String strMethodName = joinPoint.getSignature().getName();
        log.info("Class: {}, Method: {}, Args: {}", strClassName, strMethodName, joinPoint.getArgs());
        log.info("Api Response Result: {}", returnValue);
    }
}
