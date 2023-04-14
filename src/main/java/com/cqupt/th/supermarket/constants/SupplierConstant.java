package com.cqupt.th.supermarket.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author th
 * @date 2023/4/14 12:39
 */
@AllArgsConstructor
@Getter
public enum SupplierConstant {
    //使用
    USE(1, "使用"),
    //停用
    STOP(0, "停用");
    private final Integer code;
    private final String status;
}
