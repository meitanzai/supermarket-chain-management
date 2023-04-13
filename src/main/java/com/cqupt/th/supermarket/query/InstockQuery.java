package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 有现货查询
 *
 * @author TianHong
 * @date 2023/04/07
 */
@Data
public class InstockQuery implements Serializable {

    private Integer warehouseId;
    private Integer productId;
    private Integer instockCount;
    private Date startTime;
    private Date endTime;
}
