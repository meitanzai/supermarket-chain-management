package com.cqupt.th.supermarket.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author th
 * @date 2023/4/13 23:12
 */
@AllArgsConstructor
@Getter
public enum MemberConstant {
    //正常
    NORMAL(1, "正常"),
    //停用
    DISABLE(2, "停用"),
    cancellation(3, "注销");
    private final Integer code;
    private final String status;
}
