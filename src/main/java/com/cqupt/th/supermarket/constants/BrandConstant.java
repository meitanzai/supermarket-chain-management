package com.cqupt.th.supermarket.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author th
 * @date 2023/4/13 16:16
 */
@AllArgsConstructor
@Getter
public enum BrandConstant {
    SHOW(1, "展示"),
    HIDE(0, "隐藏");
    private final Integer code;
    private final String status;

}
