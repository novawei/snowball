package com.nova.common.web.exception;

import com.nova.common.core.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionAdvice {
    @Autowired
    private BasicErrorController basicErrorController;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException exception, HttpServletRequest request) {
        HttpStatus httpStatus = exception.getHttpStatus();
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, httpStatus.value());
        request.setAttribute(ApiException.ERROR_API_EXCEPTION, exception);
        return basicErrorController.error(request);
    }
}
