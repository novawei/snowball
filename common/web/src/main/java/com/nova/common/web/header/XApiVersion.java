package com.nova.common.web.header;

/**
 * X-Api-Version Header
 * 用于控制API版本
 */
public interface XApiVersion {
    String HEADER_NAME = "X-Api-Version";

    // Versions
    String V1 = "v1";
    String V2 = "v2";

    // Header Filters
    String FILTER_V1 = "X-Api-Version=v1";
    String FILTER_V2 = "X-Api-Version=v2";
}
