package com.nova.service.uac.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiException;
import com.nova.common.core.util.BeanUtils;
import com.nova.common.log.annotation.ApiLog;
import com.nova.common.web.annotation.mapping.v1.ProtectedV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1DeleteMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1PostMapping;
import com.nova.service.uac.api.entity.Role;
import com.nova.service.uac.api.entity.RolePermission;
import com.nova.service.uac.api.entity.UserRole;
import com.nova.service.uac.service.service.RolePermissionService;
import com.nova.service.uac.service.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/uac/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1PostMapping
    public void save(@Valid @RequestBody Role role) {
        if (BeanUtils.isNotNull(roleService.getById(role.getId()))) {
            throw new ApiException(ApiCode.DATA_DUPLICATED, role.getId());
        }
        roleService.save(role);
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") String id) {
        roleService.removeById(id);
    }

    @ProtectedV1GetMapping("/{id}/permissions")
    public List<String> getPermissions(@PathVariable("id") String id) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new QueryWrapper<RolePermission>().lambda();
        queryWrapper.eq(RolePermission::getRoleId, id);
        List<RolePermission> rolePermissions = rolePermissionService.list(queryWrapper);
        List<String> permissionIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rolePermissions)) {
            for (RolePermission rolePermission : rolePermissions) {
                permissionIds.add(rolePermission.getPermissionId());
            }
        }
        return permissionIds;
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1PostMapping("/{id}/permissions")
    public void addPermissions(@PathVariable("id") String id, @RequestBody List<String> permissionIds) {
        List<RolePermission> relations = new ArrayList<>();
        for (String permissionId : permissionIds) {
            relations.add(new RolePermission(id, permissionId));
        }
        rolePermissionService.saveBatch(relations);
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1DeleteMapping("/{id}/permissions")
    public void deleteRoles(@PathVariable("id") String id, @RequestBody List<String> permissionIds) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new QueryWrapper<RolePermission>().lambda();
        queryWrapper.eq(RolePermission::getRoleId, id);
        queryWrapper.in(RolePermission::getPermissionId, permissionIds);
        rolePermissionService.remove(queryWrapper);
    }
}
