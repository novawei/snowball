package com.nova.service.uac.api.entity;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String username;
    private String name;
    private String password;
    private String salt;
}