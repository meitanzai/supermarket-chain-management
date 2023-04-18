package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Product;
import com.cqupt.th.supermarket.query.ProductQuery;
import com.cqupt.th.supermarket.service.ProductService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author th
 * @date 2023/3/26 19:57
 */
@RestController
//@PreAuthorize("hasAuthority('product:product:index')")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @PostMapping("{currentPage}/{size}")
    public CommonResult getProductListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @RequestBody(required = false) ProductQuery productQuery) {
        return productService.getProductListPage(currentPage, size, productQuery);
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

    @GetMapping("categoryIdsByCategoryId/{categoryId}")
    public CommonResult getCategoryIdsByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return productService.getCategoryIdsByCategoryId(categoryId);
    }

    @GetMapping("brandIdByBrandId/{brandId}")
    public CommonResult getBrandIdByBrandId(@PathVariable("brandId") Integer brandId) {
        return productService.getBrandIdByBrandId(brandId);
    }
    @GetMapping("brandId/{brandId}/categoryId/{categoryId}")
    public CommonResult getProductListByBrandIdAndCategoryId(@PathVariable("brandId") Integer brandId, @PathVariable("categoryId") Integer categoryId) {
        return productService.getProductListByBrandIdAndCategoryId(brandId, categoryId);
    }
    @GetMapping("{id}")
    public CommonResult getProductIdById(@PathVariable("id") Integer id) {
        return productService.getProductIdById(id);

    }
    @PostMapping("isExistProductName")
    public CommonResult isExistProductName(@RequestBody Product product) {
        return productService.isExistProductName(product);
    }

    @GetMapping("brandId/{id}")
    public CommonResult getBrandIdById(@PathVariable("id") Integer id) {
        return productService.getBrandIdById(id);
    }
    @GetMapping("categoryId/{id}")
    public CommonResult getCategoryIdById(@PathVariable("id") Integer id) {
        return productService.getCategoryIdById(id);
    }
    @GetMapping("/all")
    public CommonResult getProductList() {
        return productService.getProductList();
    }
    @PutMapping("promotionalPrice/{id}/{promotionalPrice}")
    public CommonResult updatePromotionPriceById(@PathVariable("id") Integer id, @PathVariable("promotionalPrice") BigDecimal promotionalPrice) {
        return productService.updatePromotionPriceById(id, promotionalPrice);
    }
}

