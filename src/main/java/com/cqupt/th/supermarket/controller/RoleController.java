package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Role;
import com.cqupt.th.supermarket.service.RoleService;
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
@RequestMapping("/role")
@PreAuthorize("hasAuthority('permission:role:index')")
public class RoleController {
    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getRoleListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody(required = false) Role role) {
        return roleService.getRoleListPage(currentPage, pageSize, role);
    }

    @DeleteMapping("batch/{ids}")
    public CommonResult deleteBatch(@PathVariable("ids") Integer[] ids) {
        return roleService.deleteBatch(ids);
    }

    @DeleteMapping("{id}")
    public CommonResult deleteRoleById(@PathVariable("id") Integer id) {
        return roleService.deleteRoleById(id);
    }

    @PostMapping("isExistRoleName")
    public CommonResult isExistRoleName(@RequestBody Role role) {
        return roleService.isExistRoleName(role);
    }

    @PostMapping("{permissionIds}")
    public CommonResult addRole(@PathVariable("permissionIds") Integer[] permissionIds, @RequestBody Role role) {
        return roleService.addRole(permissionIds, role);
    }

    @PutMapping({"{id}/{permissionIds}"})
    public CommonResult updateRoleById(@PathVariable("id") Integer id, @PathVariable("permissionIds") Integer[] permissionIds, @RequestBody Role role) {
        return roleService.updateRoleById(id, permissionIds, role);
    }

    @GetMapping("all")
    public CommonResult getRoleList(){
        return roleService.getRoleList();
    }
}
