package com.nova.service.uac.service.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.service.dao.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
