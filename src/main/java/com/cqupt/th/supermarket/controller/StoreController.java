package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Store;
import com.cqupt.th.supermarket.query.StoreQuery;
import com.cqupt.th.supermarket.service.StoreService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/28 15:21
 */
@RestController
@CrossOrigin
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

    @GetMapping("regionIds/{regionId}")
    public CommonResult getStoreRegionIds(@PathVariable("regionId") Integer regionId) {
        return storeService.getStoreRegionIds(regionId);
    }

    @PutMapping("{id}")
    public CommonResult updateStoreById(@PathVariable("id") Integer id, @RequestBody Store store) {
        return storeService.updateStoreById(id, store);
    }

    @PostMapping
    public CommonResult addStore(@RequestBody Store store) {
        return storeService.addStore(store);
    }

    @GetMapping("getStoreIdByRegionId/{regionId}")
    public CommonResult getStoreIdByRegionId(@PathVariable("regionId") Integer regionId) {
        return storeService.getStoreIdByRegionId(regionId);
    }
    @GetMapping("getRegionIdByStoreId/{storeId}")
    public CommonResult getRegionIdByStoreId(@PathVariable("storeId") Integer storeId) {
        return storeService.getRegionIdByStoreId(storeId);
    }
}
