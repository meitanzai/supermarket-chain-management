package com.cqupt.th.supermarket.security;

import com.alibaba.fastjson.JSON;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.utils.ResponseUtil;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未经授权入口点
 * <p>
 * 未授权的统一处理方式
 * </p>
 *
 * @author qy
 * @date 2023/04/11
 */
@Component
public class UnAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        CommonResult result = new CommonResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
        ResponseUtil.renderString(response, JSON.toJSONString(result));

    }
}
