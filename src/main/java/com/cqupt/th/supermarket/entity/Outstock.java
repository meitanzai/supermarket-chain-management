package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName outstock
 */
@TableName(value = "outstock")
@Data
public class Outstock implements Serializable {
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
    private Integer outstockCount;

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
