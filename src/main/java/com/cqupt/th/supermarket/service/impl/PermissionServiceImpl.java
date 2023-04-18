package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Permission;
import com.cqupt.th.supermarket.entity.Role;
import com.cqupt.th.supermarket.service.PermissionService;
import com.cqupt.th.supermarket.mapper.PermissionMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.PermissionVo;
import com.cqupt.th.supermarket.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【permission】的数据库操作Service实现
 * @createDate 2023-04-10 22:20:50
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    @Override
    public List<PermissionVo> selectPermissionVosByRoles(List<Role> roles) {
        if (roles == null || roles.size() == 0) {
            return null;
        }
        //组装
        List<Permission> permissions = baseMapper.selectPermissionByRoles(roles);
        List<PermissionVo> permissionVoList = permissions.stream().filter(permission -> permission.getPid() == 0).map(permission -> {
            PermissionVo permissionVo = new PermissionVo();
            BeanUtils.copyProperties(permission, permissionVo);
            List<PermissionVo> children = getChildren(permission.getId(), permissions);
            if (children != null && children.size() > 0) {
                permissionVo.setChildren(children);
            } else {
                permissionVo.setChildren(null);
            }
            return permissionVo;
        }).collect(Collectors.toList());
        return permissionVoList;
    }

    @Override
    public CommonResult getPermissionListPage(Permission permission) {

        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        if (permission != null) {
            if (StringUtils.hasText(permission.getName())) {
                permissionQueryWrapper.like("name", permission.getName());
            }
        }
        List<Permission> permissions = baseMapper.selectList(permissionQueryWrapper);
        List<PermissionVo> rows = permissions.stream().filter(e -> e.getPid() == 0).map(e -> {
            PermissionVo permissionVo = new PermissionVo();
            BeanUtils.copyProperties(e, permissionVo);
            List<PermissionVo> children = getChildren(e.getId(), permissions);
            if (children != null && children.size() > 0) {
                permissionVo.setChildren(children);
            } else {
                permissionVo.setChildren(null);
            }
            return permissionVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("rows", rows);
    }

    @Override
    public CommonResult getAllPermission() {
        List<Permission> permissions = baseMapper.selectList(null);
        return CommonResult.ok().data("items", permissions);
    }


    private List<PermissionVo> getChildren(Integer id, List<Permission> permissions) {
        List<PermissionVo> collect = permissions.stream().filter(e -> e.getPid().equals(id)).map(permission -> {
            PermissionVo permissionVo = new PermissionVo();
            BeanUtils.copyProperties(permission, permissionVo);
            List<PermissionVo> children = getChildren(permission.getId(), permissions);
            if (children != null && children.size() > 0) {
                permissionVo.setChildren(children);
            } else {
                permissionVo.setChildren(null);
            }
            return permissionVo;
        }).collect(Collectors.toList());

        return collect;
    }
}




