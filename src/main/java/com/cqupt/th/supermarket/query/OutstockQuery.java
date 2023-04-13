package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/4/7 14:18
 */
@Data
public class OutstockQuery implements Serializable {

    private Integer warehouseId;
    private Integer productId;
    private Integer outstockCount;
    private Date startTime;
    private Date endTime;
}
