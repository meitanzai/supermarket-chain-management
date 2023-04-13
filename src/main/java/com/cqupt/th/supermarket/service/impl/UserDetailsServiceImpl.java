package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.th.supermarket.entity.Permission;
import com.cqupt.th.supermarket.entity.User;
import com.cqupt.th.supermarket.mapper.PermissionMapper;
import com.cqupt.th.supermarket.mapper.UserMapper;
import com.cqupt.th.supermarket.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author th
 * @date 2023/4/11 14:29
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        List<String> permissionValues = permissionMapper.selectPermissionValueByUserId(user.getId());

        return new SecurityUser(user, permissionValues);
    }
}
