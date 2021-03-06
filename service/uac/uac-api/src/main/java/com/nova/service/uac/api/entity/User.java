package com.nova.service.uac.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.nova.common.mybatis.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class User extends BaseEntity {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String password;
}