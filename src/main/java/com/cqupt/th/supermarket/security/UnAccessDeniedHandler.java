package com.cqupt.th.supermarket.security;

import com.alibaba.fastjson.JSON;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author th
 * @date 2023/4/11 14:54
 */
@Component
public class UnAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        CommonResult result = new CommonResult(HttpStatus.FORBIDDEN.value(), "权限不足");
        ResponseUtil.renderString(response, JSON.toJSONString(result));
    }
}
