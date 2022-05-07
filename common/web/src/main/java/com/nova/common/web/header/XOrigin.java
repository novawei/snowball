package com.nova.common.web.header;

public interface XOrigin {
    String HEADER_NAME = "X-Origin";

    // Origin Types
    String TYPE_GATEWAY = "Gateway";

    // Header Filters
    String FILTER_GATEWAY = "X-Origin=Gateway";
    String FILTER_NOT_GATEWAY = "X-Origin!=Gateway";
}
