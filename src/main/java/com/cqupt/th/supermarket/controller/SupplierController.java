package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Supplier;
import com.cqupt.th.supermarket.query.SupplierQuery;
import com.cqupt.th.supermarket.service.SupplierService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/4/4 22:48
 */
@RestController
@PreAuthorize("hasAuthority('supply:supply:index')")
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    @Qualifier("supplierService")
    private SupplierService supplierService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getSupplierListPage(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, @RequestBody(required = false) SupplierQuery supplierQuery) {
        return supplierService.getSupplierListPage(currentPage, pageSize, supplierQuery);
    }

    @DeleteMapping("/batch/{ids}")
    public CommonResult deleteSupplierBatch(@PathVariable("ids") Integer[] ids) {
        return supplierService.deleteSupplierBatch(ids);
    }

    @GetMapping("supplierRegionIdsByRegionId/{regionId}")
    public CommonResult getSupplierRegionIdsByRegionId(@PathVariable("regionId") Integer regionId) {
        return supplierService.getSupplierRegionIdsByRegionId(regionId);
    }

    @DeleteMapping("/{id}")
    public CommonResult deleteSupplier(@PathVariable("id") Integer id) {
        return supplierService.deleteSupplier(id);
    }

    @PostMapping
    public CommonResult addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @PutMapping("/{id}")
    public CommonResult updateSupplier(@PathVariable("id") Integer id, @RequestBody Supplier supplier) {
        return supplierService.updateSupplier(id, supplier);
    }

    @GetMapping("all")
    public CommonResult getAllSupplier() {
        return supplierService.getAllSupplier();
    }

    @PostMapping("isExistSupplierNameAndRegion")
    public CommonResult isExistSupplierNameAndRegion(@RequestBody Supplier supplier) {
        return supplierService.isExistSupplierNameAndRegion(supplier);
    }

}

