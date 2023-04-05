package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 *
 * @author TianHong
 * @date 2023/04/05
 */
@Data
public class PurchaseVo implements Serializable {

    private Integer id;
    private Integer productId;
    private String productName;
    private Integer supplierId;
    private String supplierName;
    private Integer quantity;
    private BigDecimal purchasePrice;
    private BigDecimal totalPrice;
    private Date gmtCreate;
    private Date gmtModified;
}