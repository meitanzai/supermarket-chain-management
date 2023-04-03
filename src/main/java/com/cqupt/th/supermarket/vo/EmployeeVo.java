package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/3/31 18:35
 */
@Data
public class EmployeeVo implements Serializable {
    private Integer id;
    private String name;
    private Integer sex;
    private Integer age;
    private Integer positionId;
    private String positionName;
    private Integer storeId;
    private String storeRegion;
    private Integer warehouseId;
    private String warehouseName;
    private String warehouseRegion;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModified;

}
