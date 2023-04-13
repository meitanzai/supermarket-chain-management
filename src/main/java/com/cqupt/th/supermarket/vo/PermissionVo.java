package com.cqupt.th.supermarket.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author th
 * @date 2023/4/12 15:26
 */
@Data
public class PermissionVo implements Serializable {
    private Integer id;
    private Integer pid;
    private String name;
    private Integer type;
    private String permissionValue;
    private String path;
    private String component;
    private String icon;
    private Date gmtCreate;
    private Date gmtModified;
    private List<PermissionVo> children;

}
