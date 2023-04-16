package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

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
    private Integer managerId;
    private String managerName;
    private Double area;
    private BigDecimal rent;
    private Integer status;
}
