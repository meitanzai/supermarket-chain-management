package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.BrandQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
 * @author 16075
 * @description 针对表【brand】的数据库操作Service
 * @createDate 2023-03-26 14:31:06
 */
public interface BrandService extends IService<Brand> {

    CommonResult getAllBrand();

    CommonResult getBrandListPage(Integer page, Integer size, BrandQuery brandQuery);

    CommonResult updateBrandById(Integer id, Brand brand);

    CommonResult addBrand(Brand brand);

    CommonResult deleteBrandById(Integer id);

    CommonResult deleteBrandByIds(Integer[] ids);

    CommonResult isExistBrandName(Brand brand);
}
