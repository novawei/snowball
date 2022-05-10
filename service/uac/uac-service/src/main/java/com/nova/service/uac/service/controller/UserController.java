package com.nova.service.uac.service.controller;

import com.nova.common.web.annotation.mapping.v1.ProtectedV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.ProtectedV1PostMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1GetMapping;
import com.nova.common.web.annotation.mapping.v2.ProtectedV2GetMapping;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.api.entity.UserVo;
import com.nova.service.uac.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nova.common.core.util.BeanUtils;

@RestController
@RequestMapping("/uac/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ProtectedV1GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        System.out.println("V1");
        System.out.println(user);
        return user;
    }

    @ProtectedV1PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @ProtectedV1GetMapping("/exception")
    public User getUserThrowException() {
        throw new RuntimeException("user not exists");
    }

    @PublicV1GetMapping("/{id}")
    public UserVo getVoById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        System.out.println(user);
        UserVo userVo = BeanUtils.convert(user, UserVo.class);
        return userVo;
    }
}
