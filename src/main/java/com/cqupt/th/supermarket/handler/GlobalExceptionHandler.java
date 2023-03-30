package com.cqupt.th.supermarket.handler;


import com.cqupt.th.supermarket.exception.SupermarketException;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理程序
 *
 * @author TianHong
 * @date 2023/03/18
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult error(Exception e) {
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return CommonResult.error();
    }


    @ExceptionHandler(SupermarketException.class)
    public CommonResult error(SupermarketException e) {
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return CommonResult.error().message(e.getMessage()).code(e.getCode());
    }
}
