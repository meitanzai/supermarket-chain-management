package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 16075
* @description 针对表【role】的数据库操作Mapper
* @createDate 2023-04-10 22:20:46
* @Entity com.cqupt.th.supermarket.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRolesByUserId(@Param("userId") Integer userId);
}




