package com.nova.common.security.entity;

import com.nova.service.uac.api.entity.User;
import lombok.Data;

import java.util.Set;

@Data
public class JwtUser extends User {
    private Set<String> authorities;
}
