package com.cqupt.th.supermarket.query;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author th
 * @date 2023/3/26 14:58
 */
@Data
public class BrandQuery implements Serializable {

    private String name;
    private Integer isShow;
    private Date startTime;
    private Date endTime;

}
