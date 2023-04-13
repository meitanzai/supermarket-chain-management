package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 16075
* @description 针对表【user_role】的数据库操作Mapper
* @createDate 2023-04-10 22:26:25
* @Entity com.cqupt.th.supermarket.entity.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {

    void insertBatchByUserIdAndRoleIds(@Param("userId") Integer userId,@Param("roleIds") Integer[] roleIds);
}




