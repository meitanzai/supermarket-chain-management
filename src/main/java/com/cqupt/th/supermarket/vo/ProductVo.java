package com.cqupt.th.supermarket.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author th
 * @date 2023/3/26 22:40
 */
@Data
public class ProductVo implements Serializable {

    private Integer id;
    private String barcode;
    private String name;
    private Integer categoryId;
    private String categoryName;
    private Integer brandId;
    private String brandName;
    private BigDecimal purchasePrice;
    private BigDecimal sellPrice;
    private String introduction;
    private String unit;
    private Integer isShow;
    private Date gmtCreate;
}
