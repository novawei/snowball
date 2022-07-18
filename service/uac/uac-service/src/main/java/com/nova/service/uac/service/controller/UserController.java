package com.nova.service.uac.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nova.common.log.annotation.ApiLog;
import com.nova.common.web.annotation.mapping.v1.*;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.api.entity.UserVo;
import com.nova.service.uac.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.nova.common.core.util.BeanUtils;

@Slf4j
@RestController
@RequestMapping("/uac/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ProtectedV1GetMapping("/{id}")
    public User getById(@PathVariable("id") String id) {
        User user = userService.getById(id);
        return user;
    }

    @ApiLog
    @ProtectedV1GetMapping(value = "/", params = {"username"})
    public User getByUsername(@RequestParam("username") String username) {
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
        queryWrapper.eq(User::getUsername, username);
        User user = userService.getOne(queryWrapper);
        return user;
    }

    @PublicV1GetMapping("/{id}")
    public UserVo getVoById(@PathVariable("id") String id) {
        User user = userService.getById(id);
        return BeanUtils.convert(user, UserVo.class);
    }

    @ApiLog
    @PublicV1PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") String id) {
        userService.removeById(id);
    }
}
