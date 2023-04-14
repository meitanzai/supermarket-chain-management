package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Instock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.InstockQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【instock】的数据库操作Service
* @createDate 2023-04-07 12:48:16
*/
public interface InstockService extends IService<Instock> {

    CommonResult getInstockListPage(Integer currentPage, Integer pageSize, InstockQuery instockQuery);

    CommonResult getInstockRegionIds(Integer warehouseId);

    CommonResult getWarehouseIdByRegionId(Integer regionId);

    CommonResult deleteInstockById(Integer id);

    CommonResult updateInstockById(Integer id, Instock instock);

    CommonResult addInstock(Instock instock);
}
