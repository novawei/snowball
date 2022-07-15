package com.nova.service.uac.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String username;
    private String name;
    private String password;
    private String salt;
}