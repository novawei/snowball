package com.nova.service.uac.service.controller;

import com.nova.common.log.annotation.ApiLog;
import com.nova.common.web.annotation.mapping.v1.*;
import com.nova.common.web.annotation.mapping.v2.ProtectedV2GetMapping;
import com.nova.common.web.api.ApiCode;
import com.nova.common.web.exception.ApiException;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.api.entity.UserVo;
import com.nova.service.uac.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nova.common.core.util.BeanUtils;

@Slf4j
@RestController
@RequestMapping("/uac/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ProtectedV1GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        log.debug("v1");
        log.debug("user: {}", user);
        return user;
    }

    @ApiLog
    @PublicV1PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @PublicV1GetMapping("/hello/exception")
    public User getUserThrowException() {
        throw new RuntimeException("user not exists");
    }

    @PublicV1GetMapping("/{id}")
    public UserVo getVoById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        log.debug("user: {}", user);
        UserVo userVo = BeanUtils.convert(user, UserVo.class);
        return userVo;
    }

    @PublicV1GetMapping("/{id}/name")
    public String getName(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        log.debug("user: {}", user);
        return user == null ? null : user.getName();
    }

    @ApiLog
    @PublicV1DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") Long id) {
        userService.removeById(id);
    }
}
