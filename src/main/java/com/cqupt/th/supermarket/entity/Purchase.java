package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @author 16075
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
    private Long purchaseNumber;
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
    private Date shelfLife;
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
