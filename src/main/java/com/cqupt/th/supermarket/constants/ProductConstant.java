package com.cqupt.th.supermarket.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author th
 * @date 2023/4/13 16:47
 */
@AllArgsConstructor
@Getter
public enum ProductConstant {
    SHOW(1, "展示"),
    HIDE(0, "隐藏");
    private final Integer code;
    private final String status;
}
