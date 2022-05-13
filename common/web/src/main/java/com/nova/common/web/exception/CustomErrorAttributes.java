package com.nova.common.web.exception;

import cn.hutool.extra.spring.SpringUtil;
import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiBusinessException;
import com.nova.common.web.util.I18nUtils;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.*;


@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        this.addApiCode(errorAttributes, webRequest);
        return errorAttributes;
    }

    private void addStatus(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        Integer status = (Integer)this.getAttribute(requestAttributes, "javax.servlet.error.status_code");
        if (status == null) {
            errorAttributes.put("status", 999);
            errorAttributes.put("error", "None");
        } else {
            errorAttributes.put("status", status);
            try {
                errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
            } catch (Exception e) {
                errorAttributes.put("error", "Http Status " + status);
            }
        }
    }

    private void addApiCode(Map<String, Object> errorAttributes, WebRequest webRequest) {
        Throwable error = this.getError(webRequest);
        if (error != null) {
            if (error instanceof ApiBusinessException) {
                ApiCode apiCode = ((ApiBusinessException) error).getApiCode();
                Object[] args = ((ApiBusinessException) error).getArgs();
                addApiCode(errorAttributes, webRequest, apiCode, args);
            } else {
                addApiCode(errorAttributes, webRequest, ApiCode.FAIL, null);
            }
        } else {
            Integer status = (Integer)this.getAttribute(webRequest, "javax.servlet.error.status_code");
            HttpStatus httpStatus = HttpStatus.valueOf(status);
            if (httpStatus.isError()) {
                addApiCode(errorAttributes, webRequest, ApiCode.FAIL, null);
            } else {
                addApiCode(errorAttributes, webRequest, ApiCode.OK, null);
            }
        }
    }

    private void addApiCode(Map<String, Object> errorAttributes, WebRequest webRequest, ApiCode apiCode, Object[] args) {
        Environment environment = SpringUtil.getBean("environment");
        boolean useSnakeCase = "SNAKE_CASE".equals(environment.getProperty("spring.jackson.property-naming-strategy"));
        errorAttributes.put(useSnakeCase ? "api_code" : "apiCode", apiCode.getCode());
        String messageKey = apiCode.getMessageKey();
        try {
            String message = I18nUtils.getMessage(messageKey, args);
            errorAttributes.put(useSnakeCase ? "api_message" : "apiMessage", message);
        } catch (NoSuchMessageException e) {
            e.printStackTrace();
            errorAttributes.put(useSnakeCase ? "api_message" : "apiMessage", "None");
        }
    }

    private void addPath(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        String path = (String)this.getAttribute(requestAttributes, "javax.servlet.error.request_uri");
        if (path != null) {
            errorAttributes.put("path", path);
        }
    }

    private Object getAttribute(RequestAttributes requestAttributes, String name) {
        return requestAttributes.getAttribute(name, 0);
    }
}