package com.nova.service.order.api.client;

import com.nova.common.web.annotation.mapping.v1.ProtectedV1GetMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "order-service", path = "/order/orders")
public interface OrderClient {
}
