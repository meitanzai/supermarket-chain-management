package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.query.PurchaseQuery;
import com.cqupt.th.supermarket.service.PurchaseService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/4/5 16:28
 */
@RestController
@CrossOrigin
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    @Qualifier("purchaseService")
    private PurchaseService purchaseService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getPurchaseListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody(required = false) PurchaseQuery purchaseQuery) {
        return purchaseService.getPurchaseListPage(currentPage, pageSize, purchaseQuery);
    }
}
