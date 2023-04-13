package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.UserRole;
import com.cqupt.th.supermarket.service.UserRoleService;
import com.cqupt.th.supermarket.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2023-04-10 22:20:33
*/
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




