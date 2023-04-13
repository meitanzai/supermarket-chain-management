package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.StoreQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【store(门店表)】的数据库操作Service
* @createDate 2023-03-28 15:21:41
*/
public interface StoreService extends IService<Store> {

    CommonResult getStoreListByPage(Integer currentPage, Integer pageSize, StoreQuery storeQuery);

    CommonResult deleteStoreByIds(Integer[] ids);

    CommonResult deleteStoreById(Integer id);

    CommonResult getStoreRegionIdsByRegionId(Integer regionId);

    CommonResult updateStoreById(Integer id, Store store);

    CommonResult addStore(Store store);

    CommonResult isExistStoreRegion(Store store);

    CommonResult getManagerIdByManagerId(Integer managerId);

    CommonResult getStoreRegionList();

    CommonResult getStoreIdByRegionId(Integer regionId);
//
    CommonResult getRegionIdByStoreId(Integer storeId);
}
