package com.nova.common.web.annotation;

public interface FromType {
    String HEADER_NAME = "From-Type";

    // From Types
    String GATEWAY = "Gateway";
    String FEIGN_CLIENT = "FeignClient";

    String FROM_GATEWAY = "From-Type=Gateway";
    String NOT_FROM_GATEWAY = "From-Type!=Gateway";
    String FROM_FEIGN_CLIENT = "From-Type=FeignClient";
}
