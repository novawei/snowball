package com.nova.service.uac.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.service.uac.api.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
}
