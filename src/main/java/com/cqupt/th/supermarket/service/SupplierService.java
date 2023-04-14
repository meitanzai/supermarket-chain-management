package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.SupplierQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【supplier】的数据库操作Service
* @createDate 2023-04-04 22:42:20
*/
public interface SupplierService extends IService<Supplier> {

    CommonResult getSupplierListPage(int currentPage, int pageSize, SupplierQuery supplierQuery);

    CommonResult deleteSupplierBatch(Integer[] ids);

    CommonResult getSupplierRegionIdsByRegionId(Integer regionId);

    CommonResult deleteSupplier(Integer id);

    CommonResult addSupplier(Supplier supplier);

    CommonResult updateSupplier(Integer id, Supplier supplier);

    CommonResult getAllSupplier();

    CommonResult isExistSupplierNameAndRegion(Supplier supplier);
}
