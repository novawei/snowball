package com.nova.service.uac.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiException;
import com.nova.common.log.annotation.ApiLog;
import com.nova.common.web.annotation.mapping.v1.*;
import com.nova.service.uac.api.entity.RolePermission;
import com.nova.service.uac.api.entity.User;
import com.nova.service.uac.api.entity.UserRole;
import com.nova.service.uac.api.entity.UserVo;
import com.nova.service.uac.service.service.RolePermissionService;
import com.nova.service.uac.service.service.UserRoleService;
import com.nova.service.uac.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.nova.common.core.util.BeanUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/uac/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @ProtectedV1GetMapping("/{id}")
    public User getById(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    @ProtectedV1GetMapping(value = "/", params = {"username"})
    public User getByUsername(@RequestParam("username") String username) {
        return userService.getByUsername(username);
    }

    @PublicV1GetMapping("/{id}")
    public UserVo getVoById(@PathVariable("id") String id) {
        User user = userService.getById(id);
        return BeanUtils.convert(user, UserVo.class);
    }

    @ApiLog
    @PublicV1PostMapping("/:register")
    public UserVo save(@Valid @RequestBody User user) {
        if (BeanUtils.isNotNull(userService.getByUsername(user.getUsername()))) {
            throw new ApiException(ApiCode.DATA_DUPLICATED, user.getUsername());
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        return BeanUtils.convert(user, UserVo.class);
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1PostMapping
    public UserVo saveForAdmin(@Valid @RequestBody User user) {
        if (BeanUtils.isNotNull(userService.getByUsername(user.getUsername()))) {
            throw new ApiException(ApiCode.DATA_DUPLICATED, user.getUsername());
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        return BeanUtils.convert(user, UserVo.class);
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1DeleteMapping("/{id}")
    public void removeByIdForAdmin(@PathVariable("id") String id) {
        userService.removeById(id);
    }

    @ProtectedV1GetMapping("/{id}/authorities")
    public Set<String> getAuthorities(@PathVariable("id") String id) {
        Set<String> authorities = new HashSet<>();
        // 查询角色信息
        LambdaQueryWrapper<UserRole> roleQueryWrapper = new QueryWrapper<UserRole>().lambda();
        roleQueryWrapper.eq(UserRole::getUserId, id);
        List<UserRole> userRoles = userRoleService.list(roleQueryWrapper);
        if (!CollectionUtils.isEmpty(userRoles)) {
            for (UserRole userRole : userRoles) {
                authorities.add(userRole.getRoleId());
                // 查询权限信息
                LambdaQueryWrapper<RolePermission> permissionQueryWrapper = new QueryWrapper<RolePermission>().lambda();
                permissionQueryWrapper.eq(RolePermission::getRoleId, userRole.getRoleId());
                List<RolePermission> rolePermissions = rolePermissionService.list(permissionQueryWrapper);
                if (!CollectionUtils.isEmpty(rolePermissions)) {
                    for (RolePermission rolePermission : rolePermissions) {
                        authorities.add(rolePermission.getPermissionId());
                    }
                }
            }
        }
        return authorities;
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1PostMapping("/{id}/roles")
    public void addRoles(@PathVariable("id") String id, @RequestBody List<String> roleIds) {
        List<UserRole> relations = new ArrayList<>();
        for (String roleId : roleIds) {
            relations.add(new UserRole(id, roleId));
        }
        userRoleService.saveBatch(relations);
    }

    @ApiLog
    @PreAuthorize("hasRole('ADMIN')")
    @PublicV1DeleteMapping("/{id}/roles")
    public void deleteRoles(@PathVariable("id") String id, @RequestBody List<String> roleIds) {
        LambdaQueryWrapper<UserRole> queryWrapper = new QueryWrapper<UserRole>().lambda();
        queryWrapper.eq(UserRole::getUserId, id);
        queryWrapper.in(UserRole::getRoleId, roleIds);
        userRoleService.remove(queryWrapper);
    }
}
