package com.nova.service.uac.api.client;

import com.nova.service.uac.api.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "uac-service", path = "/uac/users")
public interface UserClient {
    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id);
}
