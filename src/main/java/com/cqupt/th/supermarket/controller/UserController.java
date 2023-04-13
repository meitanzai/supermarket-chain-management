package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.User;
import com.cqupt.th.supermarket.security.SecurityUser;
import com.cqupt.th.supermarket.service.UserService;
import com.cqupt.th.supermarket.utils.CommonResult;

import com.cqupt.th.supermarket.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


/**
 * @author th
 * @date 2023/3/25 14:39
 */
@RestController
@Slf4j
//@PreAuthorize("hasAuthority('permission:user:index')")
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @PostMapping("login")
    public CommonResult login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("info")
    public CommonResult getInfo() {
        return userService.getInfo();
    }

    @PostMapping("logout")
    public CommonResult logout() {
        return userService.logout();
    }

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getUserListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody(required = false) User user) {
        return userService.getUserListPage(currentPage, pageSize, user);
    }

    @DeleteMapping("batch/{ids}")
    public CommonResult batchDelete(@PathVariable("ids") Integer[] ids) {
        return userService.batchDelete(ids);
    }

    @DeleteMapping("{id}")
    public CommonResult deleteUserById(@PathVariable("id") Integer id) {
        return userService.deleteUserById(id);
    }

    @PostMapping("isExistUserName")
    public CommonResult isExistUserName(@RequestBody User user) {
        return userService.isExistUserName(user);
    }

    @PostMapping("{roleIds}")
    public CommonResult addUser(@PathVariable("roleIds") Integer[] roleIds, @RequestBody User user) {
        return userService.addUser(roleIds, user);
    }

    @PutMapping("{id}/{roleIds}")
    public CommonResult updateUserById(@PathVariable("id") Integer id, @PathVariable("roleIds") Integer[] roleIds, @RequestBody User user) {
        return userService.updateUserById(id, roleIds, user);
    }
}

