package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Region;
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
//@PreAuthorize("hasAuthority('entity:region:index')")
@RequestMapping("/region")
public class RegionController {
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;

    @DeleteMapping("{id}")
    public CommonResult deleteRegionById(@PathVariable("id") Integer id) {
        return regionService.deleteRegionById(id);
    }

    @GetMapping("all")
    public CommonResult getRegionAll() {
        return regionService.getRegionAll();
    }

    @PostMapping()
    public CommonResult addRegion(@RequestBody Region region) {
        return regionService.addRegion(region);
    }

    @PutMapping("{id}")
    public CommonResult updateRegionById(@PathVariable("id") Integer id, @RequestBody Region region) {
        return regionService.updateRegionById(id, region);
    }
    @PostMapping("isExistRegionName")
    public CommonResult isExistRegionName(@RequestBody Region region) {
        return regionService.isExistRegionName(region);
    }

}
