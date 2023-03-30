package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author th
 * @date 2023/3/28 15:48
 */
@Data
public class StoreQuery implements Serializable {
    private Integer regionParentId;
    private String telephone;
    private String manager;
    private Double area;
    private Integer status;
}
