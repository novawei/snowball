package com.nova.service.uac.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.nova.common.mybatis.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Role extends BaseEntity {
    /**
     * 角色ID，PREFIX=ROLE_
     * 例如：
     * 管理员 = ROLE_ADMIN
     */
    @NotBlank
    @TableId(type = IdType.INPUT)
    private String id;

    @NotBlank
    private String name;
}
