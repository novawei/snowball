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
import org.springframework.web.bind.annotation.*;
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
        return user;
    }

    @PublicV1GetMapping("/{id}")
    public UserVo getVoById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        UserVo userVo = BeanUtils.convert(user, UserVo.class);
        return userVo;
    }

    @ApiLog
    @PublicV1PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @ApiLog
    @PublicV1DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") Long id) {
        userService.removeById(id);
    }
}
