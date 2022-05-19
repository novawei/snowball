package com.nova.service.uac.api.client;

import com.nova.common.web.annotation.mapping.v1.*;
import com.nova.service.uac.api.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "uac-service", path = "/uac/users")
public interface UserClient {
    @ProtectedV1GetMapping("/{id}")
    User getById(@PathVariable("id") Long id);
}
