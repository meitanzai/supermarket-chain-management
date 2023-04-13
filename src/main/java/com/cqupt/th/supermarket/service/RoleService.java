package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.utils.CommonResult;

import java.util.List;

/**
* @author 16075
* @description 针对表【role】的数据库操作Service
* @createDate 2023-04-10 22:20:46
*/
public interface RoleService extends IService<Role> {

    List<Role> selectRolesByUserId(Integer id);

    CommonResult getRoleListPage(Integer currentPage, Integer pageSize, Role role);

    CommonResult deleteBatch(Integer[] ids);

    CommonResult deleteRoleById(Integer id);

    CommonResult isExistRoleName(Role role);

    CommonResult addRole(Integer[] permissionIds, Role role);

    CommonResult updateRoleById(Integer id, Integer[] permissionIds, Role role);

    CommonResult getRoleList();

}
