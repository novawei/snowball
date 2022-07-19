package com.nova.service.uac.service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.service.dao.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
        queryWrapper.eq(User::getUsername, username);
        User user = this.getOne(queryWrapper);
        return user;
    }
}
