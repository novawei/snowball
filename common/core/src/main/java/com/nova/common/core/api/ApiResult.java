package com.nova.common.core.api;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.nova.common.core.api.ApiCode;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T payload;

    public static <T> ApiResult<T> build(T payload, int code, String message) {
        return new ApiResult<T>().setPayload(payload).setCode(code).setMessage(message);
    }

    public static <T> ApiResult<T> ok() {
        return build(null, ApiCode.OK, null);
    }

    public static <T> ApiResult<T> ok(T payload) {
        return build(payload, ApiCode.OK, null);
    }

    public static <T> ApiResult<T> ok(T payload, String message) {
        return build(payload, ApiCode.OK, message);
    }

    public static <T> ApiResult<T> fail() {
        return build(null, ApiCode.FAIL, null);
    }

    public static <T> ApiResult<T> fail(int code) {
        return build(null, code, null);
    }

    public static <T> ApiResult<T> fail(String message) {
        return build(null, ApiCode.FAIL, message);
    }
    
    public static <T> ApiResult<T> fail(int code, String message) {
        return build(null, code, message);
    }
}
