package com.nova.common.web.exception;

import com.nova.common.web.api.ApiCode;

public class ApiException extends RuntimeException {
    private ApiCode apiCode;
    private Object[] args;

    public ApiCode getApiCode() {
        return this.apiCode;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public ApiException(ApiCode apiCode, Object ...args) {
        this.apiCode = apiCode;
        this.args = args;
    }
}
