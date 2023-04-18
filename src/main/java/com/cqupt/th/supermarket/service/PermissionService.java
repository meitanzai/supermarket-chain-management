package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.entity.Role;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.PermissionVo;
import com.cqupt.th.supermarket.vo.RoleVo;

import java.util.List;

/**
* @author 16075
* @description 针对表【permission】的数据库操作Service
* @createDate 2023-04-10 22:20:50
*/
public interface PermissionService extends IService<Permission> {

    List<PermissionVo> selectPermissionVosByRoles(List<Role> roles);

    CommonResult getPermissionListPage( Permission permission);

    CommonResult getAllPermission();

}
