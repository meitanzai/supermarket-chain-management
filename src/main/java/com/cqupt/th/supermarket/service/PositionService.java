package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.PositionQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【position】的数据库操作Service
* @createDate 2023-03-31 16:12:25
*/
public interface PositionService extends IService<Position> {

    CommonResult getPositionListPage(int currentPage, int pageSize, PositionQuery positionQuery);

    CommonResult deletePositionById(Integer id);

    CommonResult updatePositionById(Integer id, Position position);

    CommonResult deletePositionByIds(Integer[] ids);

    CommonResult addPosition(Position position);

    CommonResult getPositionList();

    CommonResult isExistPositionName(Position position);

    CommonResult getPositionIdById(Integer id);
}
