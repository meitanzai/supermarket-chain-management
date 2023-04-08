package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Employee;
import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.entity.Store;
import com.cqupt.th.supermarket.entity.Warehouse;
import com.cqupt.th.supermarket.mapper.EmployeeMapper;
import com.cqupt.th.supermarket.query.WarehouseQuery;
import com.cqupt.th.supermarket.service.EmployeeService;
import com.cqupt.th.supermarket.service.RegionService;
import com.cqupt.th.supermarket.service.WarehouseService;
import com.cqupt.th.supermarket.mapper.WarehouseMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.StoreVo;
import com.cqupt.th.supermarket.vo.WarehouseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【warehouse】的数据库操作Service实现
 * @createDate 2023-03-29 16:40:00
 */
@Service("warehouseService")
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse>
        implements WarehouseService {
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public CommonResult getWarehouseByPage(Integer currentPage, Integer pageSize, WarehouseQuery warehouseQuery) {
        List<Region> list = regionService.list();
        HashMap<Integer, Region> map = new HashMap<>();
        list.stream().forEach(r -> {
            map.put(r.getId(), r);
        });
        QueryWrapper<Warehouse> warehouseWrapper = new QueryWrapper<>();
        if (warehouseQuery != null) {

            if (warehouseQuery.getRegionParentId() != null) {
                ArrayList<Integer> list1 = new ArrayList<>();
                map.forEach((k, v) -> {
                    if (v.getParentId().equals(warehouseQuery.getRegionParentId())) {
                        list1.add(v.getId());
                    }
                });
                if (list1.size() != 0) {
                    warehouseWrapper.in("region_id", list1);
                } else {
                    return CommonResult.ok().data("total", 0).data("rows", new ArrayList<>());
                }
            }
            if (warehouseQuery.getManagerId() != null) {
                warehouseWrapper.eq("manager_id", warehouseQuery.getManagerId());
            }
            if (warehouseQuery.getTel() != null) {
                warehouseWrapper.like("tel", warehouseQuery.getTel());
            }
        }
        warehouseWrapper.orderByDesc("gmt_modified");
        Page<Warehouse> warehousePage = new Page<>(currentPage, pageSize);
        baseMapper.selectPage(warehousePage, warehouseWrapper);
        List<Warehouse> records = warehousePage.getRecords();
        long total = warehousePage.getTotal();
        HashMap<Integer, String> employeeHashMap = new HashMap<>();
        employeeMapper.selectList(new QueryWrapper<Employee>().eq("store_id", 0)).stream().forEach(e -> {
            employeeHashMap.put(e.getId(), e.getName());
        });
        List<WarehouseVo> collect = records.stream().map(s -> {
            WarehouseVo warehouseVo = new WarehouseVo();
            BeanUtils.copyProperties(s, warehouseVo);
            warehouseVo.setRegionName(regionService.getRegionName(s.getRegionId(), map));
            warehouseVo.setManagerName(employeeHashMap.get(s.getManagerId()));
            return warehouseVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", collect);
    }

    @Override
    public CommonResult getWarehouseRegionIds(Integer regionId) {
        if (regionId == null) {
            return CommonResult.error().message("参数错误");
        }
        Integer[] ids = regionService.getAllRegionIds(regionId);
        if (ids == null) {
            return CommonResult.error().message("参数错误");
        }
        return CommonResult.ok().data("items", ids);
    }

    @Override
    public CommonResult deleteWarehouseById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("参数错误");
        }
        employeeMapper.updateEmployeeByWarehouseId(id);
        int i = baseMapper.deleteById(id);
        if (i == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult addWarehouse(Warehouse warehouse) {
        if (warehouse == null) {
            return CommonResult.error().message("参数错误");
        }
        int insert = baseMapper.insert(warehouse);
        if (insert == 0) {
            return CommonResult.error().message("添加失败");
        }
        return CommonResult.ok().message("添加成功");
    }

    @Override
    public CommonResult updateWarehouse(Integer id, Warehouse warehouse) {
        if (id == null || warehouse == null) {
            return CommonResult.error().message("参数错误");
        }
        warehouse.setId(id);
        int i = baseMapper.updateById(warehouse);
        if (i == 0) {
            return CommonResult.error().message("修改失败");
        }
        return CommonResult.ok().message("修改成功");
    }

    @Override
    public CommonResult getWarehouseIdByRegionId(Integer regionId) {
        if (regionId == null) {
            return CommonResult.error().message("参数错误");
        }
        Warehouse warehouse = baseMapper.selectOne(new QueryWrapper<Warehouse>().eq("region_id", regionId));
        if (warehouse == null) {
            return CommonResult.error().message("参数错误");
        }
        return CommonResult.ok().data("item", warehouse.getId());
    }

    @Override
    public CommonResult getRegionIdByWarehouseId(Integer warehouseId) {

        if (warehouseId == null) {
            return CommonResult.error().message("参数错误");
        }
        Warehouse warehouse = baseMapper.selectById(warehouseId);
        if (warehouse == null) {
            return CommonResult.ok().data("item", "");
        }
        return CommonResult.ok().data("item", warehouse.getRegionId());
    }
}




