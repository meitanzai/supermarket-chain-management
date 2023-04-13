package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Store;
import com.cqupt.th.supermarket.query.StoreQuery;
import com.cqupt.th.supermarket.service.StoreService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/28 15:21
 */
@RestController
//@PreAuthorize("hasAuthority('entity:store:index')")
@RequestMapping("/store")
public class StoreController {
    @Autowired
    @Qualifier("storeService")
    private StoreService storeService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getStoreListByPage(@PathVariable("currentPage") Integer currentPage,
                                           @PathVariable("pageSize") Integer pageSize,
                                           @RequestBody(required = false) StoreQuery storeQuery) {
        return storeService.getStoreListByPage(currentPage, pageSize, storeQuery);
    }

    @DeleteMapping("/batch/{ids}")
    public CommonResult deleteStoreByIds(@PathVariable("ids") Integer[] ids) {
        return storeService.deleteStoreByIds(ids);
    }

    @DeleteMapping("{id}")
    public CommonResult deleteStoreById(@PathVariable("id") Integer id) {
        return storeService.deleteStoreById(id);
    }

    @GetMapping("storeRegionIdsByRegionId/{regionId}")
    public CommonResult getStoreRegionIdsByRegionId(@PathVariable("regionId") Integer regionId) {
        return storeService.getStoreRegionIdsByRegionId(regionId);
    }

    @PutMapping("{id}")
    public CommonResult updateStoreById(@PathVariable("id") Integer id, @RequestBody Store store) {
        return storeService.updateStoreById(id, store);
    }

    @PostMapping
    public CommonResult addStore(@RequestBody Store store) {
        return storeService.addStore(store);
    }

    @PostMapping("isExistStoreRegion")
    public CommonResult isExistStoreRegion(@RequestBody Store store) {
        return storeService.isExistStoreRegion(store);
    }
    @GetMapping("managerIdByManagerId/{managerId}")
    public CommonResult getManagerIdByManagerId(@PathVariable("managerId") Integer managerId) {
        return storeService.getManagerIdByManagerId(managerId);
    }
    @GetMapping("storeRegionList")
    public CommonResult getStoreRegionList() {
        return storeService.getStoreRegionList();
    }

    @GetMapping("storeIdByRegionId/{regionId}")
    public CommonResult getStoreIdByRegionId(@PathVariable("regionId") Integer regionId) {
        return storeService.getStoreIdByRegionId(regionId);
    }
    @GetMapping("regionIdByStoreId/{storeId}")
    public CommonResult getRegionIdByStoreId(@PathVariable("storeId") Integer storeId) {
        return storeService.getRegionIdByStoreId(storeId);
    }
}
