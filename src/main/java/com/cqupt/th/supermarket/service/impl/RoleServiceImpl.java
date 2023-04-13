package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Permission;
import com.cqupt.th.supermarket.entity.Role;
import com.cqupt.th.supermarket.entity.RolePermission;
import com.cqupt.th.supermarket.mapper.PermissionMapper;
import com.cqupt.th.supermarket.mapper.RolePermissionMapper;
import com.cqupt.th.supermarket.service.RoleService;
import com.cqupt.th.supermarket.mapper.RoleMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【role】的数据库操作Service实现
 * @createDate 2023-04-10 22:20:46
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Role> selectRolesByUserId(Integer id) {
        if (id == null) {
            return null;
        }
        return baseMapper.selectRolesByUserId(id);
    }

    @Override
    public CommonResult getRoleListPage(Integer currentPage, Integer pageSize, Role role) {
        if (currentPage <= 0 || pageSize <= 0) {
            return CommonResult.error().message("参数错误");
        }
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        if (role != null) {
            if (role.getName() != null) {
                roleQueryWrapper.like("name", role.getName());
            }
        }
        Page<Role> rolePage = new Page<>(currentPage, pageSize);
        baseMapper.selectPage(rolePage, roleQueryWrapper);
        long total = rolePage.getTotal();
        List<Role> records = rolePage.getRecords();
        List<RoleVo> rows = records.stream().map(r -> {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(r, roleVo);
            List<Permission> permissions = permissionMapper.selectPermissionVosByRoleId(r.getId());
            roleVo.setPermissions(permissions);
            return roleVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", rows);
    }

    @Override
    public CommonResult deleteBatch(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return CommonResult.error().message("参数错误");
        }
        int i = baseMapper.deleteBatchIds(Arrays.asList(ids));
        int role_id = rolePermissionMapper.delete(new QueryWrapper<RolePermission>().in("role_id", ids));
        if (i > 0 && role_id > 0) {
            return CommonResult.ok();
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult deleteRoleById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("参数错误");
        }
        int i = baseMapper.deleteById(id);
        int role_id = rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", id));
        if (i > 0 && role_id > 0) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }


    @Override
    public CommonResult isExistRoleName(Role role) {
        if (role == null) {
            return CommonResult.error().message("参数错误");
        }
        if (role.getId() != null) {
            Role role1 = baseMapper.selectById(role.getId());
            if (role1.getName().equals(role.getName())) {
                return CommonResult.ok().data("isExist", false);
            }

        }
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("name", role.getName());
        Role role1 = baseMapper.selectOne(roleQueryWrapper);
        if (role1 != null) {
            return CommonResult.ok().data("isExist", true);
        }
        return CommonResult.ok().data("isExist", false);
    }

    @Override
    public CommonResult addRole(Integer[] permissionIds, Role role) {
        if (permissionIds == null || permissionIds.length == 0 || role == null) {
            return CommonResult.error().message("参数错误");
        }
        int i = baseMapper.insert(role);
        rolePermissionMapper.insertBatchByRoleIdAndPermissionIds(role.getId(), permissionIds);
        if (i > 0) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    @Override
    public CommonResult updateRoleById(Integer id, Integer[] permissionIds, Role role) {
        if (id == null || permissionIds == null || permissionIds.length == 0 || role == null) {
            return CommonResult.error().message("参数错误");
        }
        role.setId(id);
        int i = baseMapper.updateById(role);
        rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", id));
        rolePermissionMapper.insertBatchByRoleIdAndPermissionIds(id, permissionIds);
        if (i > 0) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    @Override
    public CommonResult getRoleList() {
        List<Role> roles = baseMapper.selectList(null);
        return CommonResult.ok().data("items", roles);
    }


}




