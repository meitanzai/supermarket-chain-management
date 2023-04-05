package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @TableName purchase
 */
@TableName(value = "purchase")
@Data
public class Purchase implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private Integer productId;

    /**
     *
     */
    private Integer supplierId;

    /**
     *
     */
    private Integer quantity;

    /**
     *
     */
    private BigDecimal purchasePrice;

    /**
     *
     */
    private BigDecimal totalPrice;

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