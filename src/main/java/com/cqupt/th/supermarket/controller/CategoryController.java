package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Category;
import com.cqupt.th.supermarket.service.CategoryService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/25 16:24
 */
@RestController
@RequestMapping("/category")
//@PreAuthorize("hasAuthority('product:category:index')")
public class CategoryController {
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;

    /**
     * 得到所有类别
     *
     * @return {@link CommonResult}
     */
    @GetMapping("/allCategoryWithTree")
    public CommonResult getAllCategoryWithTree() {
        return categoryService.getAllCategoryWithTree();
    }

    @DeleteMapping("/{id}")
    public CommonResult deleteCategoryById(@PathVariable("id") Integer id) {
        return categoryService.deleteCategoryById(id);
    }


    @PostMapping
    public CommonResult addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("/{id}")
    public CommonResult updateCategoryById(@PathVariable("id") Integer id, @RequestBody Category category) {
        return categoryService.updateCategoryById(id, category);
    }


}
