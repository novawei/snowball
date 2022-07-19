package com.nova.service.uac.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.service.uac.api.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
