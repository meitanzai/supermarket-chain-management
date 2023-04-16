package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author th
 * @date 2023/3/29 23:01
 */
@Data
public class WarehouseVo implements Serializable {
    private Integer id;
    private Integer regionId;
    private String regionName;
    private String tel;
    private BigDecimal rent;
    private Integer managerId;
    private String managerName;
}
