package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/4/6 19:17
 */
@Data
public class InventoryVo implements Serializable {
    private Integer id;
    private Integer warehouseId;
    private String warehouseRegion;
    private Integer productId;
    private String productName;
    private Integer instockCount;
    private Integer outstockCount;
    private Integer inventoryCount;
    private Date gmtCreate;
    private Date gmtModified;
}
