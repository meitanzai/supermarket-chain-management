package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @TableName product
 */
@TableName(value = "product")
@Data
public class Product implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String barcode;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer categoryId;

    /**
     *
     */
    private Integer brandId;

    /**
     * 进价
     */
    private BigDecimal purchasePrice;

    /**
     * 售价
     */
    private BigDecimal sellPrice;

    /**
     *
     */
    private String introduction;

    /**
     *
     */
    private String unit;

    /**
     *
     */
    private Integer isShow;

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