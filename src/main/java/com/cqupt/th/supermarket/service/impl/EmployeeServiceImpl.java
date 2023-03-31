package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Employee;
import com.cqupt.th.supermarket.entity.Position;
import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.entity.Store;
import com.cqupt.th.supermarket.query.EmployeeQuery;
import com.cqupt.th.supermarket.service.EmployeeService;
import com.cqupt.th.supermarket.mapper.EmployeeMapper;
import com.cqupt.th.supermarket.service.PositionService;
import com.cqupt.th.supermarket.service.RegionService;
import com.cqupt.th.supermarket.service.StoreService;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.EmployeeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【employee(员工表)】的数据库操作Service实现
 * @createDate 2023-03-31 16:17:01
 */
@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements EmployeeService {
    @Autowired
    @Qualifier("storeService")
    private StoreService storeService;
    @Autowired
    @Qualifier("positionService")
    private PositionService positionService;
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;

    @Override
    public CommonResult getEmployeeListPage(Integer currentPage, Integer pageSize, Integer positionId, EmployeeQuery employeeQuery) {
        if (currentPage == null || pageSize == null || positionId == null) {
            return CommonResult.error().message("参数错误");
        }
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.eq("position_id", positionId);
        employeeQueryWrapper.orderByDesc("gmt_modified");
        if (employeeQuery != null) {
            if (employeeQuery.getName() != null) {
                employeeQueryWrapper.like("name", employeeQuery.getName());
            }
        }
        Page<Employee> employeePage = new Page<>();
        baseMapper.selectPage(employeePage, employeeQueryWrapper);
        long total = employeePage.getTotal();
        List<Region> regionList = regionService.list();
        HashMap<Integer, Region> regionHashMap = new HashMap<>();
        regionList.forEach(region -> {
            regionHashMap.put(region.getId(), region);
        });
        HashMap<Integer, Store> storeHashMap = new HashMap<>();
        List<Store> storeList = storeService.list();
        storeList.stream().forEach(store -> {
            storeHashMap.put(store.getId(), store);
        });
        List<Position> positionList = positionService.list();
        HashMap<Integer, Position> positionHashMap = new HashMap<>();
        positionList.stream().forEach(position -> {
            positionHashMap.put(position.getId(), position);
        });
        List<Employee> records = employeePage.getRecords();
        List<EmployeeVo> rows = records.stream().map(employee -> {
            EmployeeVo employeeVo = new EmployeeVo();
            BeanUtils.copyProperties(employee, employeeVo);
            employeeVo.setPositionName(positionHashMap.get(employee.getPositionId()).getName());
            if (employee.getStoreId() == 0) {
                employeeVo.setWarehouseRegion(regionService.getRegionName(storeHashMap.get(employee.getWarehouseId()).getRegionId(), regionHashMap));

            }
            if (employee.getWarehouseId() == 0) {
                employeeVo.setStoreRegion(regionService.getRegionName(storeHashMap.get(employee.getStoreId()).getRegionId(), regionHashMap));
            }

            return employeeVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", rows);

    }

    @Override
    public CommonResult getManagerList() {

        QueryWrapper<Position> positionQueryWrapper = new QueryWrapper<>();
        positionQueryWrapper.eq("name", "超市经理");
        Position one = positionService.getOne(positionQueryWrapper);
        if (one == null) {
            return CommonResult.error().message("超市经理职位不存在");
        }
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>().eq("position_id", one.getId()).eq("warehouse_id", 0);
        List<Employee> managerList = baseMapper.selectList(queryWrapper);
        return CommonResult.ok().data("items", managerList);
    }

    @Override
    public CommonResult getWarehouseManagerList() {
        QueryWrapper<Position> positionQueryWrapper = new QueryWrapper<>();
        positionQueryWrapper.eq("name", "仓库经理");
        Position one = positionService.getOne(positionQueryWrapper);
        if (one == null) {
            return CommonResult.error().message("仓库经理职位不存在");
        }
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>().eq("position_id", one.getId()).eq("store_id", 0);
        List<Employee> managerList = baseMapper.selectList(queryWrapper);
        return CommonResult.ok().data("items", managerList);
    }
}




