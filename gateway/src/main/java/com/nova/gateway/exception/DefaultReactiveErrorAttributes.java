package com.nova.gateway.exception;

import cn.hutool.extra.spring.SpringUtil;
import com.nova.common.core.exception.ApiException;
import lombok.extern.slf4j.Slf4j;

import com.nova.common.core.api.ApiCode;
import com.nova.common.i18n.util.I18nUtils;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
public class DefaultReactiveErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);
        Environment environment = SpringUtil.getBean("environment");
        boolean useSnakeCase = "SNAKE_CASE".equals(environment.getProperty("spring.jackson.property-naming-strategy"));
        // fix Jackson Strategy for requestId
        String requestId = (String)errorAttributes.get("requestId");
        errorAttributes.remove("requestId");
        errorAttributes.put(useSnakeCase ? "request_id" : "requestId", requestId);
        // add api info
        this.addApiCode(errorAttributes, request, useSnakeCase);
        log.info("Api Response: Result={}", errorAttributes);
        return errorAttributes;
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

    private void addApiCode(Map<String, Object> errorAttributes, ServerRequest request, boolean useSnakeCase) {
        Throwable error = this.getError(request);
        ApiException apiException = findApiException(error);
        if (apiException != null) {
            ApiCode apiCode = apiException.getApiCode();
            Object[] args = apiException.getArgs();
            addApiCode(errorAttributes, request, apiCode, args, useSnakeCase);
        } else {
            addApiCode(errorAttributes, request, ApiCode.FAIL, null, useSnakeCase);
        }
    }

    private void addApiCode(Map<String, Object> errorAttributes, ServerRequest request,
                            ApiCode apiCode, Object[] args, boolean useSnakeCase) {
        errorAttributes.put(useSnakeCase ? "api_code" : "apiCode", apiCode.getCode());
        String messageKey = apiCode.getMessageKey();
        try {
            // fix: LocaleContextHolder.getLocale() not work in Webflux
            Locale locale = request.exchange().getLocaleContext().getLocale();
            String message = I18nUtils.getMessage(locale, messageKey, args);
            errorAttributes.put(useSnakeCase ? "api_message" : "apiMessage", message);
        } catch (NoSuchMessageException e) {
            log.error("No Such Message: {}", messageKey);
            // errorAttributes.put(useSnakeCase ? "api_message" : "apiMessage", "None");
        }
    }
}