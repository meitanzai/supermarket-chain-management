package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 库存
 *
 * @author TianHong
 * @TableName inventory
 * @date 2023/04/06
 */
@TableName(value = "inventory")
@Data
public class Inventory implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private Integer warehouseId;

    /**
     *
     */
    private Integer productId;

    /**
     *
     */
    private Integer instockCount;

    /**
     *
     */
    private Integer outstockCount;

    /**
     *
     */
    private Integer inventoryCount;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
