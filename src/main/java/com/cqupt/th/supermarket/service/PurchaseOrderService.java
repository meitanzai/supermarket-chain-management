package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.PurchaseOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.PurchaseOrderQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【purchase_order】的数据库操作Service
* @createDate 2023-04-06 13:56:35
*/
public interface PurchaseOrderService extends IService<PurchaseOrder> {

    CommonResult getPurchaseOrderListPage(int currentPage, int pageSize, PurchaseOrderQuery purchaseOrderQuery);

    CommonResult updatePurchaseOrder(Integer id, PurchaseOrder purchaseOrder);

    CommonResult getPurchaseOrderIsPay(Integer purchaseId);
}
