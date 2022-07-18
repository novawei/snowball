package com.nova.common.core.exception;

import com.nova.common.core.api.ApiCode;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    public static final String ERROR_API_EXCEPTION = "com.nova.common.core.exception";

    private HttpStatus httpStatus;
    private ApiCode apiCode;
    private Object[] args;

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public ApiCode getApiCode() {
        return this.apiCode;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public ApiException(ApiCode apiCode, Object ...args) {
        super();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.apiCode = apiCode;
        this.args = args;
    }

    public ApiException(HttpStatus httpStatus, ApiCode apiCode, Object ...args) {
        super();
        this.httpStatus = httpStatus;
        this.apiCode = apiCode;
        this.args = args;
    }
}
