package com.nova.service.auth.api.entity;

import com.nova.service.uac.api.entity.UserVo;
import lombok.Data;

@Data
public class TokenVo {
    private UserVo user;
    private String token;
}
