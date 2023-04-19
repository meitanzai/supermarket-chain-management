package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.Outstock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 16075
* @description 针对表【outstock】的数据库操作Mapper
* @createDate 2023-04-07 13:47:02
* @Entity com.cqupt.th.supermarket.entity.Outstock
*/
public interface OutstockMapper extends BaseMapper<Outstock> {

    List<Outstock> selectSumRecent7Days();

}




