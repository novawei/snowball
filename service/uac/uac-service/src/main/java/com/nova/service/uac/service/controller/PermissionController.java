package com.nova.service.uac.service.controller;

import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiException;
import com.nova.common.core.util.BeanUtils;
import com.nova.common.log.annotation.ApiLog;
import com.nova.common.web.annotation.mapping.v1.PublicV1DeleteMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1PostMapping;
import com.nova.service.uac.api.entity.Permission;
import com.nova.service.uac.service.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/uac/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1PostMapping
    public void save(@Valid @RequestBody Permission permission) {
        if (BeanUtils.isNotNull(permissionService.getById(permission.getId()))) {
            throw new ApiException(ApiCode.DATA_DUPLICATED, permission.getId());
        }
        permissionService.save(permission);
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") String id) {
        permissionService.removeById(id);
    }
}
