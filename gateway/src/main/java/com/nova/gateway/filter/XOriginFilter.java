package com.nova.gateway.filter;

import com.nova.common.core.header.XApiOrigin;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class XOriginFilter implements GlobalFilter, Ordered {
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        builder.headers(httpHeaders -> httpHeaders.remove(XApiOrigin.HEADER_NAME));
        builder.header(XApiOrigin.HEADER_NAME, XApiOrigin.TYPE_GATEWAY);
        return chain.filter(exchange.mutate().request(builder.build()).build());
    }

    public int getOrder() {
        return -1000;
    }
}
