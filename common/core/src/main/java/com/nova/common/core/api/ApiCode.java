package com.nova.common.core.api;

import org.springframework.lang.Nullable;

public enum ApiCode {
    // common business code defines
    OK(0, "api.code.ok"),
    FAIL(1, "api.code.fail"),

    // other business code defines
    USER_NOT_EXIST(100, "api.code.user_not_exist"),
    ;

    private static final ApiCode[] VALUES = values();
    private final int code;
    private final String messageKey;

    ApiCode(int code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessageKey() {
        return this.messageKey;
    }

    public String toString() {
        return this.code + " " + this.name();
    }

    public static ApiCode valueOf(int code) {
        ApiCode apiCode = resolve(code);
        if (apiCode == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        } else {
            return apiCode;
        }
    }

    @Nullable
    public static ApiCode resolve(int code) {
        for (int i = 0; i < VALUES.length; ++i) {
            ApiCode apiCode = VALUES[i];
            if (apiCode.getCode() == code) {
                return apiCode;
            }
        }
        return null;
    }
}