package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Warehouse;
import com.cqupt.th.supermarket.query.WarehouseQuery;
import com.cqupt.th.supermarket.service.WarehouseService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/29 16:41
 */
@RestController
//@PreAuthorize("hasAuthority('entity:warehouse:index')")
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    @Qualifier("warehouseService")
    private WarehouseService warehouseService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getWarehouseByPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody(required = false) WarehouseQuery warehouseQuery) {
        return warehouseService.getWarehouseByPage(currentPage, pageSize, warehouseQuery);
    }

    @GetMapping("warehouseRegionIdsByRegionId/{regionId}")
    public CommonResult warehouseRegionIdsByRegionId(@PathVariable("regionId") Integer regionId) {
        return warehouseService.warehouseRegionIdsByRegionId(regionId);
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
    public CommonResult updateWarehouseById(@PathVariable("id") Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.updateWarehouseById(id, warehouse);
    }

    @GetMapping("managerIdByManagerId/{managerId}")
    public CommonResult getManagerIdByManagerId(@PathVariable("managerId") Integer managerId) {
        return warehouseService.getManagerIdByManagerId(managerId);
    }
    @PostMapping("warehouseByWarehouse")
    public CommonResult getWarehouseByWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.getWarehouseByWarehouse(warehouse);
    }
    @GetMapping("warehouseRegionList")
    public CommonResult getWarehouseRegionList() {
        return warehouseService.getWarehouseRegionList();
    }
    @GetMapping("warehouseIdByRegionId/{regionId}")
    public CommonResult getWarehouseIdByRegionId(@PathVariable("regionId") Integer regionId) {
        return warehouseService.getWarehouseIdByRegionId(regionId);
    }
    @GetMapping("regionIdByWarehouseId/{warehouseId}")
    public CommonResult getRegionIdByWarehouseId(@PathVariable("warehouseId") Integer warehouseId) {
        return warehouseService.getRegionIdByWarehouseId(warehouseId);
    }

    @GetMapping("warehouseRegionAll")
    public CommonResult getWarehouseRegionAll() {
        return warehouseService.getWarehouseRegionAll();
    }
}
