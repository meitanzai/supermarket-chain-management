package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 员工
 * 员工表
 *
 * @author TianHong
 * @TableName employee
 * @date 2023/04/05
 */
@TableName(value = "employee")
@Data
public class Employee implements Serializable {
    /**
     * 员工id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 员工工号
     */
    private String workNumber;
    /**
     * 员工姓名
     */
    private String name;

    /**
     * 性别1为男，0为女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 职务
     */
    private Integer positionId;

    /**
     * 所属部门
     */
    private Integer storeId;
    private Integer warehouseId;
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
