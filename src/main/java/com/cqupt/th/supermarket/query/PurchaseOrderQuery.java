package com.cqupt.th.supermarket.query;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author th
 * @date 2023/4/6 14:47
 */
@Data
public class PurchaseOrderQuery implements Serializable {

    private Integer supplierId;
    private Long orderNumber;
    private BigDecimal totalPrice;
    private Integer isPay;
    private Date startTime;
    private Date endTime;
}
