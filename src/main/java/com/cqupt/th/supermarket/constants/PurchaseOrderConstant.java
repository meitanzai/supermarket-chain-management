package com.cqupt.th.supermarket.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author th
 * @date 2023/4/14 15:45
 */
@AllArgsConstructor
@Getter
public enum PurchaseOrderConstant {

    IS_PAY(1, "已支付"),
    NOT_PAY(2, "未支付"),
    IS_CANCEL(3, "已取消");
    private final Integer code;
    private final String status;
}
