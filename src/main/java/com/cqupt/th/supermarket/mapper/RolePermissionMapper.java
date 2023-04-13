package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 16075
* @description 针对表【role_permission】的数据库操作Mapper
* @createDate 2023-04-10 22:20:42
* @Entity com.cqupt.th.supermarket.entity.RolePermission
*/
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    void insertBatchByRoleIdAndPermissionIds(@Param("roleId") Integer roleId,@Param("permissionIds") Integer[] permissionIds);
}




