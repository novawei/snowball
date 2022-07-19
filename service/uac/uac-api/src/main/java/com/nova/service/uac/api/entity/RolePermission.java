package com.nova.service.uac.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.nova.common.mybatis.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RolePermission extends BaseEntity {
    @TableId
    private Long id;

    @NotNull
    private String roleId;

    @NotNull
    private String permissionId;

    public RolePermission(String roleId, String permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
