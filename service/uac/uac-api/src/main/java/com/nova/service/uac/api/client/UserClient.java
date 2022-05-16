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

    @PublicV1PostMapping
    void save(@RequestBody User user);

    @PublicV1PutMapping
    void updateById(@RequestBody User user);

    @PublicV1GetMapping("/hello/exception")
    User getUserThrowException();

    @PublicV1GetMapping("/{id}/name")
    String getName(@PathVariable("id") Long id);
}
