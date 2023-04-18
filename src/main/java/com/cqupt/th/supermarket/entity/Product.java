package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 产品
 *
 * @author TianHong
 * @TableName product
 * @date 2023/04/05
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
     * 售价
     */
    private BigDecimal sellPrice;
    private BigDecimal promotionalPrice;

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
