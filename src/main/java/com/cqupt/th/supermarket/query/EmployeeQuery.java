package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/3/31 18:23
 */
@Data
public class EmployeeQuery implements Serializable {
    private String workNumber;
    private String name;
    private Integer positionId;
    private Integer storeId;
    private Integer warehouseId;
    private Integer status;
    private Date startTime;
    private Date endTime;
}
