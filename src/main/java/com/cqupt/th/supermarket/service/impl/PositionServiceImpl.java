package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Position;
import com.cqupt.th.supermarket.query.PositionQuery;
import com.cqupt.th.supermarket.service.PositionService;
import com.cqupt.th.supermarket.mapper.PositionMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 16075
 * @description 针对表【position】的数据库操作Service实现
 * @createDate 2023-03-31 16:12:25
 */
@Service("positionService")
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position>
        implements PositionService {

    @Override
    public CommonResult getPositionListPage(int currentPage, int pageSize, PositionQuery positionQuery) {

        if (currentPage <= 0 || pageSize <= 0) {
            return CommonResult.error().message("参数错误");
        }
        QueryWrapper<Position> positionQueryWrapper = new QueryWrapper<>();
        positionQueryWrapper.orderByDesc("gmt_modified");
        if (positionQuery != null) {
            if (StringUtils.hasText(positionQuery.getName())) {
                positionQueryWrapper.like("name", positionQuery.getName());
            }
            if (positionQuery.getStartTime() != null) {
                positionQueryWrapper.ge("gmt_create", positionQuery.getStartTime());
            }
            if (positionQuery.getEndTime() != null) {
                positionQueryWrapper.le("gmt_create", positionQuery.getEndTime());
            }
        }
        Page<Position> positionPage = new Page<>(currentPage, pageSize);
        baseMapper.selectPage(positionPage, positionQueryWrapper);
        long total = positionPage.getTotal();
        List<Position> rows = positionPage.getRecords();
        return CommonResult.ok().data("total", total).data("rows", rows);

    }

    @Override
    public CommonResult deletePositionById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("参数错误");
        }
        int result = baseMapper.deleteById(id);
        if (result == 1) {
            return CommonResult.ok();
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult updatePositionById(Integer id, Position position) {
        if (id == null || position == null) {
            return CommonResult.error().message("参数错误");
        }
        position.setId(id);
        int result = baseMapper.updateById(position);
        if (result == 1) {
            return CommonResult.ok();
        }
        return CommonResult.error().message("更新失败");
    }

    @Override
    public CommonResult deletePositionByIds(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return CommonResult.error().message("参数错误");
        }
        int result = baseMapper.deleteBatchIds(java.util.Arrays.asList(ids));
        if (result == ids.length) {
            return CommonResult.ok();
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult addPosition(Position position) {

        if (position == null) {
            return CommonResult.error().message("参数错误");
        }
        int result = baseMapper.insert(position);
        if (result == 1) {
            return CommonResult.ok();
        }
        return CommonResult.error().message("添加失败");
    }

    @Override
    public CommonResult getPositionList() {

        QueryWrapper<Position> positionQueryWrapper = new QueryWrapper<>();
        positionQueryWrapper.orderByDesc("gmt_modified");
        List<Position> positionList = baseMapper.selectList(positionQueryWrapper);
        return CommonResult.ok().data("items", positionList);
    }
}




