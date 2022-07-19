package com.nova.service.uac.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.nova.common.mybatis.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRole extends BaseEntity {
    @TableId
    private Long id;

    @NotNull
    private String userId;

    @NotNull
    private String roleId;

    public UserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
