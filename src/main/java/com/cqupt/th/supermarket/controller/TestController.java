package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author th
 * @date 2023/3/25 14:39
 */
@RestController
@Slf4j
@CrossOrigin
public class TestController {

    @PostMapping("/user/login")
    @ApiOperation(value = "登录测试")
    public CommonResult login() {
        log.info("有人登录");
        return CommonResult.ok().data("token", "cary");
    }

    @GetMapping("/user/info")
    @ApiOperation(value = "信息测试")
    public CommonResult getInfo(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        log.info("有人获取信息" + authorization);
        return CommonResult.ok().data("roles", "[]").data("name", "cary").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("/user/logout")
    @ApiOperation(value = "退出测试")
    public CommonResult logout() {
        log.info("有人退出");
        return CommonResult.ok();
    }
}

