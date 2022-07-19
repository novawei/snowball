package com.nova.service.uac.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.nova.common.mybatis.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Permission extends BaseEntity {
    /**
     * 权限ID应该描述清楚所属的模块和功能
     * 例如：
     * 获取用户列表的权限 = uac.users.list
     * 创建用户的权限    = uac.users.save
     * 获取用户的权限    = uac.users.get
     * 编辑用户的权限    = uac.users.edit
     * 删除用户的权限    = uac.users.delete
     */
    @NotNull
    @TableId(type = IdType.INPUT)
    private String id;

    @NotNull
    private String name;
}
