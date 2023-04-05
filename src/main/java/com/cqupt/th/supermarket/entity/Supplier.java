package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 供应商
 *
 * @author TianHong
 * @TableName supplier
 * @date 2023/04/05
 */
@TableName(value ="supplier")
@Data
public class Supplier implements Serializable {
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
    private String contactPerson;

    /**
     * 
     */
    private String tel;

    /**
     * 
     */
    private Integer regionId;

    /**
     * 
     */
    private Integer isUse;

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