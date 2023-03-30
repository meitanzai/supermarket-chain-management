package com.cqupt.th.supermarket.constants;


import lombok.AllArgsConstructor;

import lombok.Getter;

/**
 * 商店常数
 *
 * @author th
 * @date 2023/3/28 15:15
 */
@Getter
@AllArgsConstructor

public enum StoreConstant {
    //正常营业
    OPEN(1, "营业"),
    //装修
    DECORATE(2, "装修"),
    //关闭
    CLOSE(3, "关闭");
    private final Integer code;
    private final String status;

}