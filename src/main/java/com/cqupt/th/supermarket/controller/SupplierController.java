package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.query.SupplierQuery;
import com.cqupt.th.supermarket.service.SupplierService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/4/4 22:48
 */
@RestController
@CrossOrigin
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    @Qualifier("supplierService")
    private SupplierService supplierService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getSupplierListPage(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, @RequestBody(required = false) SupplierQuery supplierQuery) {
        return supplierService.getSupplierListPage(currentPage, pageSize, supplierQuery);
    }

}
