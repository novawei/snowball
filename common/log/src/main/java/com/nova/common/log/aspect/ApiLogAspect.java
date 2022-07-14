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
    public void doBefore(final JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("Api Request: HttpMethod={} URI={}", request.getMethod(), request.getRequestURI());
        // TODO: log当前请求的执行人
        String strClassName = joinPoint.getTarget().getClass().getName();
        String strMethodName = joinPoint.getSignature().getName();
        log.info("Api Request: Class={} Method={} Args={}", strClassName, strMethodName, joinPoint.getArgs());
    }

    /**
     * Do after.
     *
     * @param joinPoint   the join point
     * @param returnValue the return value
     */
    @AfterReturning(pointcut = "logAnnotation()", returning = "returnValue")
    public void doAfter(final JoinPoint joinPoint, final Object returnValue) {
        log.info("Api Response: Result={}", returnValue);
    }
}
