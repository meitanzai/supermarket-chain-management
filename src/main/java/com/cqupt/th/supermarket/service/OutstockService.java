package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Outstock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.OutstockQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

import java.util.List;

/**
* @author 16075
* @description 针对表【outstock】的数据库操作Service
* @createDate 2023-04-07 12:48:21
*/
public interface OutstockService extends IService<Outstock> {

    CommonResult getOutstockListPage(Integer currentPage, Integer pageSize, OutstockQuery outstockQuery);

    CommonResult getWarehouseIdByRegionId(Integer regionId);

    CommonResult deleteOutstockById(Integer id);

    CommonResult updateOutstockById(Integer id, Outstock outstock);

    CommonResult addOutstock(Outstock outstock);

    CommonResult getOutstockRegionIds(Integer warehouseId);

    List<Outstock> selectSumRecent7Days();
}
