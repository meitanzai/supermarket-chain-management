package com.cqupt.th.supermarket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Role;
import com.cqupt.th.supermarket.entity.User;
import com.cqupt.th.supermarket.entity.UserRole;
import com.cqupt.th.supermarket.mapper.UserRoleMapper;
import com.cqupt.th.supermarket.utils.MemuHelper;
import com.cqupt.th.supermarket.security.SecurityUser;
import com.cqupt.th.supermarket.service.PermissionService;
import com.cqupt.th.supermarket.service.RoleService;
import com.cqupt.th.supermarket.service.UserService;
import com.cqupt.th.supermarket.mapper.UserMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.utils.JwtUtil;
import com.cqupt.th.supermarket.vo.PermissionVo;
import com.cqupt.th.supermarket.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-04-10 22:20:37
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;
    @Autowired
    @Qualifier("permissionService")
    private PermissionService permissionService;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public CommonResult login(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return CommonResult.error().message("用户名或密码不能为空");
        }
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        Integer id = principal.getUser().getId();
        String username = principal.getUser().getUsername();
        String token = jwtUtil.createJwtToken(id, username);
        return CommonResult.ok().data("token", token);
    }

    @Override
    public CommonResult getInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return CommonResult.error().message("未登录");
        }
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        User user = principal.getUser();
        List<Role> roles = roleService.selectRolesByUserId(user.getId());
        if (roles == null) {
            return CommonResult.error().message("未分配角色");
        }
        List<PermissionVo> permissionVoList = permissionService.selectPermissionVosByRoles(roles);
        if (permissionVoList == null) {
            return CommonResult.error().message("未分配权限");
        }
        List<JSONObject> result = MemuHelper.bulid(permissionVoList);
        return CommonResult.ok().data("name", user.getUsername()).data("roles", roles).data("menus", result).data("userId", user.getId());
    }

    @Override
    public CommonResult logout() {
        SecurityContextHolder.clearContext();
        return CommonResult.ok();
    }

    @Override
    public CommonResult getUserListPage(Integer currentPage, Integer pageSize, User user) {
        if (currentPage == null || pageSize == null) {
            return CommonResult.error().message("分页参数不能为空");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (user != null) {
            if (StringUtils.hasText(user.getUsername())) {
                userQueryWrapper.like("username", user.getUsername());
            }
        }
        Page<User> userPage = new Page<>(currentPage, pageSize);
        baseMapper.selectPage(userPage, userQueryWrapper);
        long total = userPage.getTotal();
        List<User> records = userPage.getRecords();
        List<UserVo> rows = records.stream().map(r -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(r, userVo);
            List<Role> roles = roleService.selectRolesByUserId(r.getId());
            userVo.setRoles(roles);
            return userVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", rows);
    }

    @Override
    public CommonResult batchDelete(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return CommonResult.error().message("请选择要删除的数据");
        }
        int user_id = userRoleMapper.delete(new QueryWrapper<UserRole>().in("user_id", ids));
        int i = baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (i > 0 && user_id > 0) {
            return CommonResult.ok();
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult deleteUserById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("请选择要删除的数据");
        }
        int user_id = userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", id));
        int i = baseMapper.deleteById(id);
        if (i > 0 && user_id > 0) {
            return CommonResult.ok();
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult isExistUserName(User user) {
        if (user == null) {
            return CommonResult.error().message("参数错误");
        }
        if (user.getId() != null) {
            User user1 = baseMapper.selectById(user.getId());
            if (user1 == null) {
                return CommonResult.error().message("参数错误");
            }
            if (user1.getUsername().equals(user.getUsername())) {
                return CommonResult.ok().data("item", false);
            }

        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", user.getUsername());
        User user1 = baseMapper.selectOne(userQueryWrapper);
        if (user1 != null) {
            return CommonResult.ok().data("item", true);
        }
        return CommonResult.ok().data("item", false);
    }

    @Override
    public CommonResult addUser(Integer[] roleIds, User user) {
        if (roleIds == null || roleIds.length == 0 || user == null) {
            return CommonResult.error().message("参数错误");
        }
        if (user.getPassword().length() < 6) {
            return CommonResult.error().message("密码长度不能小于6位");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        int i = baseMapper.insert(user);
        userRoleMapper.insertBatchByUserIdAndRoleIds(user.getId(), roleIds);
        if (i > 0) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    @Override
    public CommonResult updateUserById(Integer id, Integer[] roleIds, User user) {
        if (id == null || roleIds == null || roleIds.length == 0 || user == null) {
            return CommonResult.error().message("参数错误");
        }
        user.setId(id);
        if (user.getPassword().length() < 6) {
            return CommonResult.error().message("密码长度不能小于6位");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        int i = baseMapper.updateById(user);
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", id));
        userRoleMapper.insertBatchByUserIdAndRoleIds(id, roleIds);
        if (i > 0) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }
}




