package com.nova.service.auth.api.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthVo {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
