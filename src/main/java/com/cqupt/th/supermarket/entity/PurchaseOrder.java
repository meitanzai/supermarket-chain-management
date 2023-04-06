package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName purchase_order
 */
@TableName(value ="purchase_order")
@Data
public class PurchaseOrder implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    //雪花算法生成

    private Long orderNumber;

    /**
     *
     */
    private Integer purchaseId;

    /**
     *
     */
    private Integer supplierId;

    /**
     *
     */
    private BigDecimal totalPrice;

    /**
     *
     */
    private Integer isPay;

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
