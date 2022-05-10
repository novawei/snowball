package com.nova.common.core.exception;

import com.nova.common.core.api.ApiCode;

public class ApiBusinessException extends RuntimeException {
    private ApiCode apiCode;
    private Object[] args;

    public ApiCode getApiCode() {
        return this.apiCode;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public ApiBusinessException(ApiCode apiCode, Object ...args) {
        this.apiCode = apiCode;
        this.args = args;
    }
}
