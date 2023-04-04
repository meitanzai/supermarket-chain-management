package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.util.Date;

/**
 * @author th
 * @date 2023/4/4 22:54
 */
@Data
public class SupplierQuery {

    private String name;
    private String contactPerson;
    private String tel;
    private Integer regionId;
    private Date startTime;
    private Date endTime;
}

