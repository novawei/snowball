package com.nova.service.uac.service.controller;

import com.nova.common.web.annotation.ProtectedGetMapping;
import com.nova.common.web.annotation.PublicGetMapping;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.api.entity.UserVo;
import com.nova.service.uac.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nova.common.core.api.ApiResult;
import com.nova.common.core.util.BeanUtils;

@RestController
@RequestMapping("/uac/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ProtectedGetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        System.out.println(user);
        return user;
    }

    @PublicGetMapping("/{id}")
    public ApiResult<UserVo> getUserVoById(@PathVariable Long id) {
        User user = userService.getById(id);
        System.out.println(user);
        UserVo userVo = BeanUtils.convert(user, UserVo.class);
        return ApiResult.ok(userVo);
    }
}
