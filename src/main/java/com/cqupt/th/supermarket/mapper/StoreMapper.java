package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
* @author 16075
* @description 针对表【store(门店表)】的数据库操作Mapper
* @createDate 2023-03-31 19:46:28
* @Entity com.cqupt.th.supermarket.entity.Store
*/
public interface StoreMapper extends BaseMapper<Store> {

    void updateRegionIdByRegionIds(@Param("regionIds") Set<Integer> regionIds);

    void updateManager(@Param("storeId") Integer storeId, @Param("managerId") Integer managerId);

    void updateStoreManagerAll(@Param("managerId") Integer managerId);

}




