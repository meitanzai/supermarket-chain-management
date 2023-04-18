package com.cqupt.th.supermarket.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author th
 * @date 2023/4/18 10:49
 */
@AllArgsConstructor
@Getter
public enum OutstockConstant {
    //调货出库
    OUTSTOCK_TRANSFER(0, "调货出库"),
    //出售出库
    OUTSTOCK_SELL(1, "出售出库");
    private final Integer code;
    private final String type;
}
