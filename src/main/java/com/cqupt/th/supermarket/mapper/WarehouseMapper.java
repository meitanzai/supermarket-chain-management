package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
* @author 16075
* @description 针对表【warehouse】的数据库操作Mapper
* @createDate 2023-03-31 19:45:21
* @Entity com.cqupt.th.supermarket.entity.Warehouse
*/
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    void updateRegionIdByRegionIds(@Param("regionIds") Set<Integer> regionIds);

    void updateManager(@Param("warehouseId") Integer warehouseId,@Param("managerId") Integer managerId);

    void updateWarehouseManagerAll(@Param("managerId") Integer managerId);
}




