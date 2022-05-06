package com.nova.service.user.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.service.user.api.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
