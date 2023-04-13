package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Employee;
import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.entity.Store;
import com.cqupt.th.supermarket.entity.Warehouse;
import com.cqupt.th.supermarket.mapper.EmployeeMapper;
import com.cqupt.th.supermarket.mapper.WarehouseMapper;
import com.cqupt.th.supermarket.query.StoreQuery;
import com.cqupt.th.supermarket.service.RegionService;
import com.cqupt.th.supermarket.service.StoreService;
import com.cqupt.th.supermarket.mapper.StoreMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.RegionVo;
import com.cqupt.th.supermarket.vo.StoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【store(门店表)】的数据库操作Service实现
 * @createDate 2023-03-28 15:21:41
 */
@Service("storeService")
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store>
        implements StoreService {
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private WarehouseMapper warehouseMapper;


    @Override
    public CommonResult getStoreListByPage(Integer currentPage, Integer pageSize, StoreQuery storeQuery) {
        List<Region> list = regionService.list();
        HashMap<Integer, Region> map = new HashMap<>();
        list.stream().forEach(r -> {
            map.put(r.getId(), r);
        });
        QueryWrapper<Store> storeQueryWrapper = new QueryWrapper<>();
        if (storeQuery != null) {
            if (storeQuery.getRegionId() != null) {
                storeQueryWrapper.eq("region_id", storeQuery.getRegionId());
            }
            if (storeQuery.getTelephone() != null) {
                storeQueryWrapper.like("telephone", storeQuery.getTelephone());
            }
            if (storeQuery.getManagerId() != null) {
                storeQueryWrapper.eq("manager_id", storeQuery.getManagerId());
            }
            if (storeQuery.getStatus() != null) {
                storeQueryWrapper.eq("status", storeQuery.getStatus());
            }
        }
        storeQueryWrapper.orderByDesc("gmt_modified");
        Page<Store> storePage = new Page<>(currentPage, pageSize);
        baseMapper.selectPage(storePage, storeQueryWrapper);
        long total = storePage.getTotal();
        List<Store> records = storePage.getRecords();
        HashMap<Integer, String> employeeMap = new HashMap<>();
        List<Employee> employeeList = employeeMapper.selectList(new QueryWrapper<Employee>().eq("warehouse_id", 0));
        employeeList.stream().forEach(e -> {
            employeeMap.put(e.getId(), e.getName());
        });
        List<StoreVo> collect = records.stream().map(s -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(s, storeVo);
            storeVo.setRegionName(regionService.getRegionName(s.getRegionId(), map));
            String managerName = employeeMap.get(s.getManagerId());
            if (managerName != null) {
                storeVo.setManagerName(managerName);
            } else {
                storeVo.setManagerName("");
            }
            return storeVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", collect);
    }

    @Override
    public CommonResult deleteStoreByIds(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return CommonResult.error().message("参数错误");
        }
        //TODO
        //删除仓库
        List<Store> stores = baseMapper.selectBatchIds(Arrays.asList(ids));
        List<Integer> regionIds = stores.stream().map(Store::getRegionId).collect(Collectors.toList());
        warehouseMapper.delete(new QueryWrapper<Warehouse>().in("region_id", regionIds));
        int result = baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (result == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult deleteStoreById(Integer id) {

        if (id == null) {
            return CommonResult.error().message("参数错误");
        }
        //TODO
        Store store = baseMapper.selectById(id);
        warehouseMapper.delete(new QueryWrapper<Warehouse>().eq("region_id", store.getRegionId()));
        int result = baseMapper.deleteById(id);
        if (result == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult getStoreRegionIdsByRegionId(Integer regionId) {
        if (regionId == null) {
            return CommonResult.error().message("参数错误");
        }
        if (regionId == 0) {
            return CommonResult.ok().data("items", new Integer[]{});
        }
        Integer[] ids = regionService.getRegionIdsById(regionId);
        if (ids == null) {
            return CommonResult.error().message("参数错误");
        }
        return CommonResult.ok().data("items", ids);

    }

    @Override
    public CommonResult updateStoreById(Integer id, Store store) {
        if (id == null || store == null) {
            return CommonResult.error().message("参数错误");
        }
        store.setId(id);
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(store, warehouse);
        Store store1 = baseMapper.selectById(store);
        if (store1.getRegionId() != 0) {
            Warehouse warehouse1 = warehouseMapper.selectOne(new QueryWrapper<Warehouse>().eq("region_id", store1.getRegionId()));
            if (warehouse1 != null) {
                if (store.getTelephone() != null) {
                    warehouse1.setTel(store.getTelephone());
                }
                if (store.getRegionId() != null) {
                    warehouse1.setRegionId(store.getRegionId());
                }
            }
            int result1 = warehouseMapper.updateById(warehouse1);
            if (result1 == 0) {
                return CommonResult.error().message("更新失败");
            }
        }
        if (store1.getRegionId() == 0 && store.getRegionId() != 0) {
            warehouse.setTel(store.getTelephone());
            warehouse.setManagerId(0);
            int insert = warehouseMapper.insert(warehouse);
            if (insert == 0) {
                return CommonResult.error().message("添加失败");
            }
        }
        int result = baseMapper.updateById(store);
        if (result == 0) {
            return CommonResult.error().message("更新失败");
        }
        return CommonResult.ok().message("更新成功");
    }

    @Override
    public CommonResult addStore(Store store) {

        if (store == null) {
            return CommonResult.error().message("参数错误");
        }
        if (store.getRegionId() != null && store.getRegionId() != 0) {
            Warehouse warehouse = new Warehouse();
            BeanUtils.copyProperties(store, warehouse);
            warehouse.setTel(store.getTelephone());
            warehouse.setManagerId(0);
            int insert = warehouseMapper.insert(warehouse);
            if (insert == 0) {
                return CommonResult.error().message("添加失败");
            }
        }
        int result = baseMapper.insert(store);
        if (result == 0) {
            return CommonResult.error().message("添加失败");
        }
        return CommonResult.ok().message("添加成功");
    }

    @Override
    public CommonResult getStoreByStore(Store store) {
        if (store == null) {
            return CommonResult.error().message("参数错误");
        }
        if (store.getRegionId() != null && store.getRegionId() == 0) {
            return CommonResult.ok().data("item", null);
        }
        if (store.getId() != null) {
            Store store1 = baseMapper.selectById(store.getId());
            if (store1 == null) {
                return CommonResult.error().message("参数错误");
            }
            if (store1.getRegionId().equals(store.getRegionId())) {
                return CommonResult.ok().data("item", null);
            }
        }
        Store store1 = baseMapper.selectOne(new QueryWrapper<Store>().eq("region_id", store.getRegionId()));
        if (store1 == null) {
            return CommonResult.ok().data("item", null);
        }
        return CommonResult.ok().data("item", store1);
    }

    @Override
    public CommonResult getManagerIdByManagerId(Integer managerId) {
        if (managerId == null) {
            return CommonResult.error().message("参数错误");
        }
        if (managerId == 0) {
            return CommonResult.ok().data("item", null);
        }
        Employee employee = employeeMapper.selectById(managerId);
        if (employee == null) {
            return CommonResult.ok().data("item", null);
        }
        return CommonResult.ok().data("item", managerId);
    }

    @Override
    public CommonResult getStoreRegionList() {
        List<Store> stores = baseMapper.selectList(new QueryWrapper<Store>().ne("region_id", 0));
        List<Integer> ids = stores.stream().map(s -> s.getRegionId()).collect(Collectors.toList());
        List<RegionVo> items = regionService.getRegionAll(ids);
        return CommonResult.ok().data("items", items);
    }

    @Override
    public CommonResult getStoreIdByRegionId(Integer regionId) {

        if (regionId == null) {
            return CommonResult.error().message("参数错误");
        }
        Store store = baseMapper.selectOne(new QueryWrapper<Store>().eq("region_id", regionId));
        if (store == null) {
            return CommonResult.error().message("参数错误");
        }
        Integer id = store.getId();
        return CommonResult.ok().data("item", id);
    }

    //
    @Override
    public CommonResult getRegionIdByStoreId(Integer storeId) {

        if (storeId == null) {
            return CommonResult.error().message("参数错误");
        }
        Store store = baseMapper.selectById(storeId);
        if (store == null) {
            return CommonResult.ok().data("item", null);
        }
        Integer regionId = store.getRegionId();
        return CommonResult.ok().data("item", regionId);
    }


}




