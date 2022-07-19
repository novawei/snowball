package com.nova.service.uac.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.nova.common.mybatis.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class User extends BaseEntity {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}