package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * @author th
 * @date 2023/3/27 19:20
 */
@Data
public class RegionVo implements Serializable {
    private Integer id;
    private String name;
    private String fullName;
}
