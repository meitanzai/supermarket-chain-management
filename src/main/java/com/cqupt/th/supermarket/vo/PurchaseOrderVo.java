package com.cqupt.th.supermarket.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author th
 * @date 2023/4/6 14:51
 */
@Data
public class PurchaseOrderVo implements Serializable {

    private Integer id;
    private Integer supplierId;
    private String supplierName;
    private BigDecimal totalPrice;
    private Integer isPay;
    private Date gmCreate;
    private Date gmModified;
}
