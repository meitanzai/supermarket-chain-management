package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 门店表
 *
 * @TableName store
 */
@TableName(value = "store")
@Data
public class Store implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 门店地址
     */
    private Integer regionId;

    /**
     * 门店电话
     */
    private String telephone;

    /**
     *
     */
    private String manager;

    /**
     *
     */
    private Double area;

    /**
     *
     */
    private Integer status;

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