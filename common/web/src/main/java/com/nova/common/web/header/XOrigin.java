package com.nova.common.web.header;

/**
 * X-Origin Header
 * 用于控制微服务内部接口（ProtectedXXXMapping）和公共接口（PublicXXXMapping）
 */
public interface XOrigin {
    String HEADER_NAME = "X-Origin";

    // Origin Types
    String TYPE_GATEWAY = "Gateway";

    // Header Filters
    String FILTER_GATEWAY = "X-Origin=Gateway";
    String FILTER_NOT_GATEWAY = "X-Origin!=Gateway";
    String FILTER_FEIGN = "X-Origin=Feign";
}
