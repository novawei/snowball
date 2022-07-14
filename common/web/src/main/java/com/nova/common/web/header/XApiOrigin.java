package com.nova.common.web.header;

/**
 * X-Api-Origin Header
 * 用于控制微服务内部接口（ProtectedXXXMapping）和公共接口（PublicXXXMapping）
 */
public interface XApiOrigin {
    String HEADER_NAME = "X-Api-Origin";

    // Origin Types
    String TYPE_GATEWAY = "Gateway";

    // Header Filters
    String FILTER_GATEWAY = "X-Api-Origin=Gateway";
    String FILTER_FEIGN = "X-Api-Origin=Feign";
}
