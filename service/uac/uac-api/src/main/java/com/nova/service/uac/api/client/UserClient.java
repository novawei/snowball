package com.nova.service.uac.api.client;

import com.nova.common.web.annotation.mapping.v1.ProtectedV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.ProtectedV1PostMapping;
import com.nova.service.uac.api.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "uac-service", path = "/uac/users")
public interface UserClient {
    @ProtectedV1GetMapping("/{id}")
    User getUserById(@PathVariable("id") Long id);

    @ProtectedV1PostMapping
    void saveUser(@RequestBody User user);

    @ProtectedV1GetMapping("/exception")
    User getUserThrowException();
}
