package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.RolePermission;
import com.cqupt.th.supermarket.service.RolePermissionService;
import com.cqupt.th.supermarket.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【role_permission】的数据库操作Service实现
* @createDate 2023-04-10 22:20:42
*/
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




