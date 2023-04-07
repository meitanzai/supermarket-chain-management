package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.query.RegionQuery;
import com.cqupt.th.supermarket.service.RegionService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/27 18:13
 */
@RestController
@CrossOrigin
@RequestMapping("/region")
public class RegionController {
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;

    @PostMapping("{currentPage}/{size}")
    public CommonResult getRegionByPage(@PathVariable("currentPage") Integer currentPage,
                                        @PathVariable("size") Integer size, @RequestBody(required = false) RegionQuery regionQuery
    ) {
        return regionService.getRegionByPage(currentPage, size, regionQuery);
    }

    @DeleteMapping("/batch/{ids}")
    public CommonResult deleteRegionByIds(@PathVariable("ids") Integer[] ids) {
        return regionService.deleteRegionByIds(ids);
    }

    @DeleteMapping("{id}")
    public CommonResult deleteRegionById(@PathVariable("id") Integer id) {
        return regionService.deleteRegionById(id);
    }

    @GetMapping("tree")
    public CommonResult getRegionTree() {
        return regionService.getRegionTree();
    }

    @GetMapping("all")
    public CommonResult getRegionAll() {
        return regionService.getRegionAll();
    }

    @GetMapping("ids/{id}")
    public CommonResult getRegionIds(@PathVariable("id") Integer id) {
        return regionService.getRegionIds(id);
    }

    @PostMapping()
    public CommonResult addRegion(@RequestBody Region region) {
        return regionService.addRegion(region);
    }

    @PutMapping("{id}")
    public CommonResult updateRegionById(@PathVariable("id") Integer id, @RequestBody Region region) {
        return regionService.updateRegionById(id, region);
    }

    @GetMapping("storeRegionAll")
    public CommonResult getStoreRegionAll() {
        return regionService.getStoreRegionAll();
    }
    @GetMapping("warehouseRegionAll")
    public CommonResult getWarehouseRegionAll() {
        return regionService.getWarehouseRegionAll();
    }
    @GetMapping("businessStoreRegionAll")
    public CommonResult getBusinessStoreRegionAll() {
        return regionService.getBusinessStoreRegionAll();
    }
    @GetMapping("storeAndWarehouseRegionAll")
    public CommonResult getStoreAndWarehouseRegionAll() {
        return regionService.getStoreAndWarehouseRegionAll();
    }
}
