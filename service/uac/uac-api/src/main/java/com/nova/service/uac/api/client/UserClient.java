package com.nova.service.uac.api.client;

import com.nova.common.web.annotation.mapping.v1.ProtectedV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.ProtectedV1PostMapping;
import com.nova.common.web.annotation.mapping.v1.ProtectedV1PutMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1GetMapping;
import com.nova.service.uac.api.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "uac-service", path = "/uac/users")
public interface UserClient {
    @ProtectedV1GetMapping("/{id}")
    User getById(@PathVariable("id") Long id);

    @ProtectedV1PostMapping
    void save(@RequestBody User user);

    @ProtectedV1PutMapping
    void updateById(@RequestBody User user);

    @PublicV1GetMapping("/hello/exception")
    User getUserThrowException();

    @PublicV1GetMapping("/{id}/name")
    String getName(@PathVariable("id") Long id);
}
