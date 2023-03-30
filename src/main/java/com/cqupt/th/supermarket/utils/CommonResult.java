package com.cqupt.th.supermarket.utils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author th
 * @date 2023/1/20 14:03
 */
@Data
public class CommonResult {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 把构造方法私有
     */
    private CommonResult() {
    }


    /**
     * 成功静态方法
     *
     * @return {@link CommonResult}
     */
    public static CommonResult ok() {
        CommonResult r = new CommonResult();
        r.setSuccess(true);
        r.setCode(HttpStatus.OK.value());
        r.setMessage("成功");
        return r;
    }


    /**
     * 失败静态方法
     *
     * @return {@link CommonResult}
     */
    public static CommonResult error() {
        CommonResult r = new CommonResult();
        r.setSuccess(false);
        return r;
    }

    public CommonResult success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public CommonResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public CommonResult code(Integer code) {
        this.setCode(code);
        return this;
    }

    public CommonResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public CommonResult data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
