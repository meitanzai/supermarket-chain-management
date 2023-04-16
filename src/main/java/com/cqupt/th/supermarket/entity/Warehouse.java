package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 仓库
 *
 * @author TianHong
 * @TableName warehouse
 * @date 2023/04/05
 */
@TableName(value = "warehouse")
@Data
public class Warehouse implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 仓库地址
     */
    private Integer regionId;

    /**
     * 负责人
     */
    private Integer managerId;

    /**
     * 联系电话
     */
    private String tel;

    /**
     *
     */
    private BigDecimal rent;
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
