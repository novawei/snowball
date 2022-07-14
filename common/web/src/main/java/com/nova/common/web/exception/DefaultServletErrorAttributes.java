package com.nova.common.web.exception;

import cn.hutool.extra.spring.SpringUtil;
import com.nova.common.web.api.ApiCode;
import com.nova.common.web.util.I18nUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.*;


@Slf4j
@Component
public class DefaultServletErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        this.addApiCode(errorAttributes, webRequest);
        log.info("Api Response Result : {}", errorAttributes);
        return errorAttributes;
    }

    private void addApiCode(Map<String, Object> errorAttributes, WebRequest webRequest) {
        Throwable error = this.getError(webRequest);
        if (error != null) {
            if (error instanceof ApiException) {
                ApiCode apiCode = ((ApiException) error).getApiCode();
                Object[] args = ((ApiException) error).getArgs();
                addApiCode(errorAttributes, apiCode, args);
            } else {
                addApiCode(errorAttributes, ApiCode.FAIL, null);
            }
        } else {
            Integer status = (Integer)errorAttributes.get("status");
            HttpStatus httpStatus = HttpStatus.valueOf(status);
            if (httpStatus.isError()) {
                addApiCode(errorAttributes, ApiCode.FAIL, null);
            } else {
                addApiCode(errorAttributes, ApiCode.OK, null);
            }
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