package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.PurchaseOrder;
import com.cqupt.th.supermarket.query.PurchaseOrderQuery;
import com.cqupt.th.supermarket.service.PurchaseOrderService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/4/5 21:59
 */
@RestController
@CrossOrigin
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {
    @Autowired
    @Qualifier("purchaseOrderService")
    private PurchaseOrderService purchaseOrderService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getPurchaseOrderListPage(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, @RequestBody(required = false) PurchaseOrderQuery purchaseOrderQuery) {
        return purchaseOrderService.getPurchaseOrderListPage(currentPage, pageSize, purchaseOrderQuery);
    }
    @PutMapping("{id}")
    public CommonResult updatePurchaseOrder(@PathVariable("id") Integer id, @RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.updatePurchaseOrder(id, purchaseOrder);
    }

    @GetMapping("/isPay/{purchaseId}")
    public CommonResult getPurchaseOrderIsPay(@PathVariable("purchaseId") Integer purchaseId) {
        return purchaseOrderService.getPurchaseOrderIsPay(purchaseId);
    }
    @GetMapping("{purchaseId}")
    public CommonResult getPurchaseOrder(@PathVariable("purchaseId") Integer purchaseId) {
        return purchaseOrderService.getPurchaseOrder(purchaseId);
    }
}
