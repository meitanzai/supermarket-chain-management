package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Instock;
import com.cqupt.th.supermarket.query.InstockQuery;
import com.cqupt.th.supermarket.service.InstockService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/4/7 12:49
 */
@RestController
@PreAuthorize("hasAuthority('inventory:instock:index')")
@RequestMapping("/instock")
public class InstockController {
    @Autowired
    @Qualifier("instockService")
    private InstockService instockService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getInstockListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody(required = false) InstockQuery instockQuery) {
        return instockService.getInstockListPage(currentPage, pageSize, instockQuery);
    }
    @GetMapping("instockRegionIds/{warehouseId}")
    public CommonResult getInstockRegionIds(@PathVariable("warehouseId") Integer warehouseId) {
        return instockService.getInstockRegionIds(warehouseId);
    }
    @GetMapping("warehouseIdByRegionId/{regionId}")
    public CommonResult getWarehouseIdByRegionId(@PathVariable("regionId") Integer regionId) {
        return instockService.getWarehouseIdByRegionId(regionId);
    }
    @DeleteMapping("{id}")
    public CommonResult deleteInstockById(@PathVariable("id") Integer id) {
        return instockService.deleteInstockById(id);
    }
    @PutMapping("{id}")
    public CommonResult updateInstockById(@PathVariable("id") Integer id, @RequestBody Instock instock) {
        return instockService.updateInstockById(id, instock);
    }
    @PostMapping
    public CommonResult addInstock(@RequestBody Instock instock) {
        return instockService.addInstock(instock);
    }
}
