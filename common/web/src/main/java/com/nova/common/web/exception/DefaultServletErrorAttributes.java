package com.nova.common.web.exception;

import cn.hutool.extra.spring.SpringUtil;
import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiException;
import com.nova.common.i18n.util.I18nUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.RequestDispatcher;
import java.util.*;


@Slf4j
@Component
public class DefaultServletErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        this.addApiCode(errorAttributes, webRequest);
        log.info("Api Response: Result={}", errorAttributes);
        return errorAttributes;
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    private ApiException findApiException(WebRequest webRequest) {
        Throwable error = this.getAttribute(webRequest, ApiException.ERROR_API_EXCEPTION);
        ApiException apiException = findApiException(error);
        if (apiException != null) {
            return apiException;
        }
        error = this.getAttribute(webRequest, RequestDispatcher.ERROR_EXCEPTION);
        apiException = findApiException(error);
        if (apiException != null) {
            return apiException;
        }
        error = this.getError(webRequest);
        return findApiException(error);
    }

    private ApiException findApiException(Throwable error) {
        if (error == null) {
            return null;
        }
        if (error instanceof ApiException) {
            return (ApiException) error;
        }
        if (error.getCause() != null && error.getCause() instanceof ApiException) {
            return (ApiException) error.getCause();
        }
        return null;
    }

    private void addApiCode(Map<String, Object> errorAttributes, WebRequest webRequest) {
        ApiException apiException = findApiException(webRequest);
        if (apiException != null) {
            ApiCode apiCode = apiException.getApiCode();
            Object[] args = apiException.getArgs();
            addApiCode(errorAttributes, apiCode, args);
        } else {
            addApiCode(errorAttributes, ApiCode.FAIL, null);
        }
    }

    private void addApiCode(Map<String, Object> errorAttributes, ApiCode apiCode, Object[] args) {
        Environment environment = SpringUtil.getBean("environment");
        boolean useSnakeCase = "SNAKE_CASE".equals(environment.getProperty("spring.jackson.property-naming-strategy"));
        errorAttributes.put(useSnakeCase ? "api_code" : "apiCode", apiCode.getCode());
        String messageKey = apiCode.getMessageKey();
        try {
            String message = I18nUtils.getMessage(messageKey, args);
            errorAttributes.put(useSnakeCase ? "api_message" : "apiMessage", message);
        } catch (NoSuchMessageException e) {
            log.warn("No Such Message: {}", messageKey);
            // errorAttributes.put(useSnakeCase ? "api_message" : "apiMessage", "None");
        }
    }
}