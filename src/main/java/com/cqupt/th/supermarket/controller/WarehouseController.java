package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Warehouse;
import com.cqupt.th.supermarket.query.WarehouseQuery;
import com.cqupt.th.supermarket.service.WarehouseService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/29 16:41
 */
@RestController
@CrossOrigin
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    @Qualifier("warehouseService")
    private WarehouseService warehouseService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getWarehouseByPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody(required = false) WarehouseQuery warehouseQuery) {
        return warehouseService.getWarehouseByPage(currentPage, pageSize, warehouseQuery);
    }
    @GetMapping("regionIds/{regionId}")
    public CommonResult getWarehouseRegionIds(@PathVariable("regionId") Integer regionId) {
        return warehouseService.getWarehouseRegionIds(regionId);
    }
    @DeleteMapping("{id}")
    public CommonResult deleteWarehouseById(@PathVariable("id") Integer id) {
        return warehouseService.deleteWarehouseById(id);
    }
    @PostMapping
    public CommonResult addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse);
    }
    @PutMapping("{id}")
    public CommonResult updateWarehouse(@PathVariable("id") Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.updateWarehouse(id, warehouse);
    }
}
