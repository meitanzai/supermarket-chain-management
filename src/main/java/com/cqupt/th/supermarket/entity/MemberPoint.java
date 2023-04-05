package com.cqupt.th.supermarket.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 成员点
 *
 * @author TianHong
 * @TableName member_point
 * @date 2023/04/05
 */
@TableName(value ="member_point")
@Data
public class MemberPoint implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer memberId;

    /**
     * 
     */
    private Integer point;

    /**
     * 
     */
    private String remark;

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