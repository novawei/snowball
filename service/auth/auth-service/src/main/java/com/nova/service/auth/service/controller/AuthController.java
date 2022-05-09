package com.nova.service.auth.service.controller;

import com.nova.common.web.annotation.mapping.v1.ProtectedV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1GetMapping;
import com.nova.common.web.annotation.mapping.v2.ProtectedV2GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nova.common.core.api.ApiResult;
import com.nova.common.core.util.BeanUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

}
