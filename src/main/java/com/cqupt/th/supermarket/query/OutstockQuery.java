package com.cqupt.th.supermarket.query;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Integer toWarehouseId;
    private Date startTime;
    private Date endTime;
}
