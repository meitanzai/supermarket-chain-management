package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.query.InventoryQuery;
import com.cqupt.th.supermarket.service.InventoryService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/4/6 19:14
 */
@RestController
@PreAuthorize("hasAuthority('inventory:inventory:index')")
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    @Qualifier("inventoryService")
    private InventoryService inventoryService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getInventoryListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody(required = false) InventoryQuery inventoryQuery) {
        return inventoryService.getInventoryListPage(currentPage, pageSize, inventoryQuery);
    }

    @GetMapping("getWarehouseIdByRegionId/{regionId}")
    public CommonResult getWarehouseIdByRegionId(@PathVariable("regionId") Integer regionId) {
        return inventoryService.getWarehouseIdByRegionId(regionId);
    }
}
