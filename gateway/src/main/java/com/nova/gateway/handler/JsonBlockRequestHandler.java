package com.nova.gateway.handler;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nova.common.core.api.ApiCode;
import com.nova.common.i18n.util.I18nUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class JsonBlockRequestHandler implements BlockRequestHandler {
    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable error) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        // timestamp, status, error, exception, message, api_code, api_message
        Map<String, Object> errorAttributes = this.getErrorAttributes(request, error);
        Environment environment = SpringUtil.getBean("environment");
        boolean useSnakeCase = "SNAKE_CASE".equals(environment.getProperty("spring.jackson.property-naming-strategy"));
        // fix Jackson Strategy for requestId
        String requestId = (String)errorAttributes.get("requestId");
        errorAttributes.remove("requestId");
        errorAttributes.put(useSnakeCase ? "request_id" : "requestId", requestId);
        // add api info
        this.addApiCode(errorAttributes, serverWebExchange, error, useSnakeCase);
        log.info("Api Response: Result={}", errorAttributes);
        return ServerResponse
                .status((int)errorAttributes.get("status"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorAttributes));
    }

    public Map<String, Object> getErrorAttributes(ServerHttpRequest request, Throwable error) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("path", request.getURI().getPath());

        MergedAnnotation<ResponseStatus> responseStatusAnnotation = MergedAnnotations
                .from(error.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(ResponseStatus.class);
        HttpStatus errorStatus = determineHttpStatus(error, responseStatusAnnotation);
        errorAttributes.put("status", errorStatus.value());
        errorAttributes.put("error", errorStatus.getReasonPhrase());
        errorAttributes.put("requestId", request.getId());
        return errorAttributes;
    }

    private HttpStatus determineHttpStatus(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException) error).getStatus();
        }
        return responseStatusAnnotation.getValue("code", HttpStatus.class).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void addApiCode(Map<String, Object> errorAttributes, ServerWebExchange exchange, Throwable error, boolean useSnakeCase) {
        if (error != null) {
            if (error instanceof BlockException) {
                // 可以进一步异常细分 FlowException...
                addApiCode(errorAttributes, exchange, ApiCode.SENTINEL_BLOCKED, null, useSnakeCase);
            } else {
                addApiCode(errorAttributes, exchange, ApiCode.FAIL, null, useSnakeCase);
            }
        } else {
            Integer status = (Integer)errorAttributes.get("status");
            HttpStatus httpStatus = HttpStatus.valueOf(status);
            if (httpStatus.isError()) {
                addApiCode(errorAttributes, exchange, ApiCode.FAIL, null, useSnakeCase);
            } else {
                addApiCode(errorAttributes, exchange, ApiCode.OK, null, useSnakeCase);
            }
        }
    }

    private void addApiCode(Map<String, Object> errorAttributes, ServerWebExchange exchange,
                            ApiCode apiCode, Object[] args, boolean useSnakeCase) {
        errorAttributes.put(useSnakeCase ? "api_code" : "apiCode", apiCode.getCode());
        String messageKey = apiCode.getMessageKey();
        try {
            // fix: LocaleContextHolder.getLocale() not work in Webflux
            Locale locale = exchange.getLocaleContext().getLocale();
            String message = I18nUtils.getMessage(locale, messageKey, args);
            errorAttributes.put(useSnakeCase ? "api_message" : "apiMessage", message);
        } catch (NoSuchMessageException e) {
            log.warn("No Such Message: {}", messageKey);
            // errorAttributes.put(useSnakeCase ? "api_message" : "apiMessage", "None");
        }
    }
}
