package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商店
 * 门店表
 *
 * @author TianHong
 * @TableName store
 * @date 2023/04/05
 */
@TableName(value ="store")
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
    private Integer managerId;

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