package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【user】的数据库操作Service
* @createDate 2023-04-10 22:20:37
*/
public interface UserService extends IService<User> {


    CommonResult login(User user);

    CommonResult getInfo();

    CommonResult logout();

    CommonResult getUserListPage(Integer currentPage, Integer pageSize, User user);

    CommonResult batchDelete(Integer[] ids);

    CommonResult deleteUserById(Integer id);

    CommonResult isExistUserName(User user);

    CommonResult addUser(Integer[] roleIds, User user);

    CommonResult updateUserById(Integer id, Integer[] roleIds, User user);
}
