package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Brand;
import com.cqupt.th.supermarket.query.BrandQuery;
import com.cqupt.th.supermarket.service.BrandService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/26 13:06
 */
@RestController
@CrossOrigin
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    @Qualifier("brandService")
    private BrandService brandService;

    @GetMapping("/all")
    public CommonResult getAllBrand() {
        return brandService.getAllBrand();
    }

    @PostMapping("{currentPage}/{size}")
    public CommonResult getBrandByPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @RequestBody(required = false) BrandQuery brandQuery
    ) {
        return brandService.getBrandByPage(currentPage, size, brandQuery);
    }

    @PutMapping("{id}")
    public CommonResult updateBrandById(@PathVariable("id") Integer id, @RequestBody Brand brand) {
        return brandService.updateBrandById(id, brand);
    }

    @GetMapping({"/{id}"})
    public CommonResult getBrandById(@PathVariable("id") Integer id) {
        return brandService.getBrandById(id);

    }

    @GetMapping("isShow/{id}")
    public CommonResult getBrandIsShow(@PathVariable("id") Integer id) {
        return brandService.getBrandIsShow(id);
    }
    @PostMapping
    public CommonResult addBrand(@RequestBody Brand brand) {
        return brandService.addBrand(brand);
    }

    @DeleteMapping({"/{id}"})
    public CommonResult deleteBrandById(@PathVariable("id") Integer id) {
        return brandService.deleteBrandById(id);
    }

    @DeleteMapping("/batch/{ids}")
    public CommonResult deleteBrandByIds(@PathVariable("ids") Integer[] ids) {
        return brandService.deleteBrandByIds(ids);
    }
}

