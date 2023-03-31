package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author th
 * @date 2023/3/29 22:12
 */
@Data
public class WarehouseQuery implements Serializable {
    private Integer regionParentId;
    private String tel;
    private Integer managerId;
    private String name;

}
