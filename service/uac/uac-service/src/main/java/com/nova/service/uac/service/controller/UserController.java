package com.nova.service.uac.service.controller;

import com.nova.common.web.annotation.mapping.v1.ProtectedV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1GetMapping;
import com.nova.common.web.annotation.mapping.v2.ProtectedV2GetMapping;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.api.entity.UserVo;
import com.nova.service.uac.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ProtectedV1GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        System.out.println("V1");
        System.out.println(user);
        return user;
    }

    @ProtectedV1GetMapping("/exception")
    public User getUserThrowException() {
        throw new RuntimeException("user not exists");
    }


    /**
     * 不同API版本例子
     * @param id
     * @return
     */
    @ProtectedV2GetMapping("/{id}")
    public User v2_getUserById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        System.out.println("V2");
        System.out.println(user);
        return user;
    }

    @PublicV1GetMapping("/{id}")
    public ApiResult<UserVo> getUserVoById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        System.out.println(user);
        UserVo userVo = BeanUtils.convert(user, UserVo.class);
        return ApiResult.ok(userVo);
    }
}
