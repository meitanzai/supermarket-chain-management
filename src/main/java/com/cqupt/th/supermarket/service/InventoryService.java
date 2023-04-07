package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.InventoryQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【inventory】的数据库操作Service
* @createDate 2023-04-06 19:09:26
*/
public interface InventoryService extends IService<Inventory> {

    CommonResult getInventoryListPage(Integer currentPage, Integer pageSize, InventoryQuery inventoryQuery);

    CommonResult getWarehouseIdByRegionId(Integer regionId);
}
