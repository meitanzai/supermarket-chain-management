package com.cqupt.th.supermarket.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/4/4 22:57
 */
@Data
public class SupplierVo implements Serializable {

    private Integer id;
    private String name;
    private String contactPerson;
    private String tel;
    private Integer regionId;
    private String regionName;
    private Date gmtCreate;
    private Date gmtModified;
}
