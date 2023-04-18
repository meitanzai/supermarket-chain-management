package com.cqupt.th.supermarket.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author th
 * @date 2023/4/17 12:14
 */
@AllArgsConstructor
@Getter
public enum IncomeExpenseConstant {
    INCOME(1, "收入"),
    EXPENSE(0, "支出");
    private final Integer code;
    private final String type;
}
