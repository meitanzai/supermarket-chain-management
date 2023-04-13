package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.th.supermarket.entity.Role;
import com.cqupt.th.supermarket.vo.PermissionVo;
import com.cqupt.th.supermarket.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 16075
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2023-04-10 22:20:50
* @Entity com.cqupt.th.supermarket.entity.Permission
*/
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> selectPermissionValueByUserId(@Param("userId") Integer userId);

    List<Permission> selectPermissionByRoles(@Param("roles")List<Role> roles);

    List<Permission> selectPermissionVosByRoleId(@Param("roleId") Integer roleId);
}




