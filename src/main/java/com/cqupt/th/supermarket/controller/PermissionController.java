package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Permission;
import com.cqupt.th.supermarket.service.PermissionService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/4/11 13:54
 */
@RestController
@RequestMapping("/permission")
@PreAuthorize("hasAuthority('permission:permission:index')")
public class PermissionController {
    @Autowired
    @Qualifier("permissionService")
    private PermissionService permissionService;

    @PostMapping()
    public CommonResult getPermissionListPage( @RequestBody(required = false) Permission permission) {
        return permissionService.getPermissionListPage( permission);
    }
    @GetMapping("all")
    public CommonResult getAllPermission() {
        return permissionService.getAllPermission();
    }

}
