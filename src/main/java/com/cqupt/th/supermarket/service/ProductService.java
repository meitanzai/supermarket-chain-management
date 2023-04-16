package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.ProductQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【product】的数据库操作Service
* @createDate 2023-03-26 19:55:57
*/
public interface ProductService extends IService<Product> {

    CommonResult getProductListPage(Integer currentPage, Integer size, ProductQuery productQuery);

    CommonResult updateProductById(Integer id, Product product);

    CommonResult deleteProductByIds(Integer[] ids);

    CommonResult deleteProductById(Integer id);

    CommonResult addProduct(Product product);

    CommonResult getCategoryIdsByCategoryId(Integer categoryId);

    CommonResult getBrandIdByBrandId(Integer brandId);

    CommonResult getProductListByBrandIdAndCategoryId(Integer brandId, Integer categoryId);

    CommonResult getProductIdById(Integer id);

    CommonResult isExistProductName(Product product);

    CommonResult getBrandIdById(Integer id);

    CommonResult getCategoryIdById(Integer id);

    CommonResult getProductList();

}
