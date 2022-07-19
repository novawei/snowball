package com.nova.service.uac.service.controller;

import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiException;
import com.nova.common.log.annotation.ApiLog;
import com.nova.common.web.annotation.mapping.v1.*;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.api.entity.UserVo;
import com.nova.service.uac.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.nova.common.core.util.BeanUtils;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/uac/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ProtectedV1GetMapping("/{id}")
    public User getById(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    @ProtectedV1GetMapping(value = "/", params = {"username"})
    public User getByUsername(@RequestParam("username") String username) {
        return userService.getByUsername(username);
    }

    @PublicV1GetMapping("/{id}")
    public UserVo getVoById(@PathVariable("id") String id) {
        User user = userService.getById(id);
        return BeanUtils.convert(user, UserVo.class);
    }

    @ApiLog
    @PublicV1PostMapping("/:register")
    public UserVo save(@Valid @RequestBody User user) {
        if (BeanUtils.isNotNull(userService.getByUsername(user.getUsername()))) {
            throw new ApiException(ApiCode.USR_DUPLICATED, user.getUsername());
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        return BeanUtils.convert(user, UserVo.class);
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1PostMapping
    public UserVo saveForAdmin(@Valid @RequestBody User user) {
        if (BeanUtils.isNotNull(userService.getByUsername(user.getUsername()))) {
            throw new ApiException(ApiCode.USR_DUPLICATED, user.getUsername());
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        return BeanUtils.convert(user, UserVo.class);
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1DeleteMapping("/{id}")
    public void removeByIdForAdmin(@PathVariable("id") String id) {
        userService.removeById(id);
    }
}
