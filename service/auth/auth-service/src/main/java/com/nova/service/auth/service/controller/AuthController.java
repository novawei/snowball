package com.nova.service.auth.service.controller;

import com.nova.common.core.util.BeanUtils;
import com.nova.common.log.annotation.ApiLog;
import com.nova.common.security.util.CacheUtils;
import com.nova.common.security.util.JwtUtils;
import com.nova.common.security.util.SecurityUtils;
import com.nova.common.web.annotation.mapping.v1.PublicV1DeleteMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1PostMapping;
import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiException;
import com.nova.service.auth.api.entity.AuthVo;
import com.nova.service.auth.api.entity.TokenVo;
import com.nova.service.uac.api.client.UserClient;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.api.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserClient userClient;

    @ApiLog
    @PublicV1PostMapping("/token")
    public TokenVo createToken(@RequestBody AuthVo authVo) {
        User user = userClient.getByUsername(authVo.getUsername());
        if (user == null) {
            throw new ApiException(ApiCode.USR_PWD_INVALID);
        }
        TokenVo tokenVo = new TokenVo();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(authVo.getPassword(), user.getPassword())) {
            String token = JwtUtils.buildToken(user.getId());
            tokenVo.setUser(BeanUtils.convert(user, UserVo.class));
            tokenVo.setToken(token);
            CacheUtils.cacheUser(user);
        } else {
            throw new ApiException(ApiCode.USR_PWD_INVALID);
        }
        return tokenVo;
    }

    @ApiLog
    @PublicV1DeleteMapping("/token")
    public void deleteToken() {
        User user = SecurityUtils.getLoginUser();
        if (user != null) {
            CacheUtils.deleteUser(user.getId());
        }
    }
}
