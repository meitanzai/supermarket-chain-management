package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 员工表
 * @TableName employee
 */
@TableName(value ="employee")
@Data
public class Employee implements Serializable {
    /**
     * 员工id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
    private Date gmtCreate;

    /**
     * 
     */
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}