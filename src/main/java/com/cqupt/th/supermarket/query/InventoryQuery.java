package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/4/6 19:26
 */
@Data
public class InventoryQuery implements Serializable {
    private Integer warehouseId;
    private Integer productId;
    private Integer inventoryCount;
    private Date startTime;
    private Date endTime;
}
