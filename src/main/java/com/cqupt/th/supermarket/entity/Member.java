package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @TableName member
 */
@TableName(value = "member")
@Data
public class Member implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String cardNumber;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String tel;

    /**
     *
     */
    private Integer sex;

    /**
     *
     */
    private BigDecimal balance;

    /**
     *
     */
    private Integer status;

    /**
     *
     */
    private Integer point;

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