package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author th
 * @date 2023/3/28 16:44
 */
@Data
public class StoreVo implements Serializable{
    private Integer id;
    private Integer regionId;
    private String regionName;
    private String telephone;
    private String manager;
    private Double area;
    private Integer status;
}
