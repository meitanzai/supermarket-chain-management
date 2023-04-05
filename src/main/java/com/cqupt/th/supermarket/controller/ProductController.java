package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Product;
import com.cqupt.th.supermarket.query.ProductQuery;
import com.cqupt.th.supermarket.service.ProductService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/26 19:57
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @PostMapping("{currentPage}/{size}")
    public CommonResult getProductByPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @RequestBody(required = false) ProductQuery productQuery) {
        return productService.getProductByPage(currentPage, size, productQuery);
    }

    @PutMapping("{id}")
    public CommonResult updateProductById(@PathVariable("id") Integer id, @RequestBody Product product) {
        return productService.updateProductById(id, product);
    }

    @DeleteMapping("batch/{ids}")
    public CommonResult deleteProductByIds(@PathVariable("ids") Integer[] ids) {
        return productService.deleteProductByIds(ids);
    }

    @DeleteMapping("{id}")
    public CommonResult deleteProductById(@PathVariable("id") Integer id) {
        return productService.deleteProductById(id);
    }

    @PostMapping
    public CommonResult addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("categoryIds/{id}")
    public CommonResult getCategoryIds(@PathVariable("id") Integer id) {
        return productService.getCategoryIds(id);
    }

    @GetMapping("brandId/{brandId}/categoryId/{categoryId}")
    public CommonResult getProductByBrandIdAndCategoryId(@PathVariable("brandId") Integer brandId, @PathVariable("categoryId") Integer categoryId) {
        return productService.getProductByBrandIdAndCategoryId(brandId, categoryId);
    }
    @GetMapping("{id}")
    public CommonResult getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }
}

