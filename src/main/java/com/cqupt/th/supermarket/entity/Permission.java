package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 许可
 *
 * @author TianHong
 * @TableName permission
 * @date 2023/04/10
 */
@TableName(value = "permission")
@Data
public class Permission implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private Integer pid;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer type;

    /**
     *
     */
    private String permissionValue;

    /**
     *
     */
    private String path;

    /**
     *
     */
    private String component;

    /**
     *
     */
    private String icon;

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
