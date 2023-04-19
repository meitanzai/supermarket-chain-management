package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author th
 * @date 2023/3/26 20:17
 */
@Data
public class ProductQuery implements Serializable {

    private String name;
    private Integer categoryId;
    private Integer brandId;
    private Integer isShow;
    private BigDecimal sellPrice;
    private BigDecimal promotionalPrice;
    private Date startTime;
    private Date endTime;

}
