package com.cqupt.th.supermarket.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author th
 * @date 2023/4/14 10:54
 */
@AllArgsConstructor
@Getter
public enum EmployeeConstant {
    //在职
    ON_JOB(1, "在职"),
    //停职
    STOP_JOB(2, "停职"),
    //离职
    LEAVE_JOB(3, "离职");
    private final Integer code;
    private final String status;
}
