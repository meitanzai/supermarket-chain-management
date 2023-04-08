package com.cqupt.th.supermarket.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.entity.Store;
import com.cqupt.th.supermarket.entity.Warehouse;
import com.cqupt.th.supermarket.mapper.StoreMapper;
import com.cqupt.th.supermarket.mapper.WarehouseMapper;
import com.cqupt.th.supermarket.query.RegionQuery;
import com.cqupt.th.supermarket.service.RegionService;
import com.cqupt.th.supermarket.mapper.RegionMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.RegionListVo;
import com.cqupt.th.supermarket.vo.RegionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【region】的数据库操作Service实现
 * @createDate 2023-03-27 18:12:27
 */
@Service("regionService")
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region>
        implements RegionService {
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private WarehouseMapper warehouseMapper;

    @Override
    public CommonResult getRegionByPage(Integer currentPage, Integer size, RegionQuery regionQuery) {
        List<Region> regions = baseMapper.selectList(new QueryWrapper<Region>().ne("level", 5));
        HashMap<Integer, Region> map = new HashMap<>(regions.size());
        regions.stream().forEach(region -> {
            map.put(region.getId(), region);
        });
        QueryWrapper<Region> regionQueryWrapper = new QueryWrapper<>();
        if (regionQuery != null) {
            if (regionQuery.getParentId() != null) {
                regionQueryWrapper.eq("parent_id", regionQuery.getParentId());
            }
        }
        regionQueryWrapper.orderByDesc("gmt_modified");
        Page<Region> regionPage = new Page<>(currentPage, size);
        baseMapper.selectPage(regionPage, regionQueryWrapper);
        long total = regionPage.getTotal();
        List<Region> records = regionPage.getRecords();
        List<RegionVo> collect = records.stream().map(region -> {
            RegionVo regionVo = new RegionVo();
            BeanUtils.copyProperties(region, regionVo);
            regionVo.setFullName(getName(region, map));
            return regionVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", collect);
    }

    @Override
    public CommonResult deleteRegionByIds(Integer[] ids) {

        if (ids.length == 0) {
            return CommonResult.error().message("请选择要删除的数据");
        }
        HashSet<Integer> set = new HashSet<>();
        //递归获得所有子节点
        for (Integer id : ids) {
            Set<Integer> sonIds = getSonIds(id, set);
            set.addAll(sonIds);
        }
        storeMapper.updateRegionIdByRegionIds(set);
        warehouseMapper.updateRegionIdByRegionIds(set);

        int i = baseMapper.deleteBatchIds(set);
        if (i > 0) {
            return CommonResult.ok().message("删除成功");
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult deleteRegionById(Integer id) {

        if (id == null) {
            return CommonResult.error().message("请选择要删除的数据");
        }
        HashSet<Integer> set = new HashSet<>();
        //递归获得所有子节点
        Set<Integer> ids = getSonIds(id, set);
        storeMapper.updateRegionIdByRegionIds(ids);
        warehouseMapper.updateRegionIdByRegionIds(ids);
        int i = baseMapper.deleteBatchIds(ids);
        if (i > 0) {
            return CommonResult.ok().message("删除成功");
        }
        return CommonResult.error().message("删除失败");

    }

    private Set<Integer> getSonIds(Integer id, HashSet<Integer> set) {
        set.add(id);
        List<Region> regions = baseMapper.selectList(new QueryWrapper<Region>().eq("parent_id", id));
        if (regions.size() > 0) {
            regions.stream().forEach(region -> {
                set.add(region.getId());
                getSonIds(region.getId(), set);
            });
        }
        return set;
    }

    @Override
    public CommonResult getRegionTree() {
        List<Region> regions = baseMapper.selectList(new QueryWrapper<Region>().ne("level", 5));
        List<RegionListVo> collect = regions.stream().map(region -> {
            RegionListVo regionListVo = new RegionListVo();
            BeanUtils.copyProperties(region, regionListVo);
            return regionListVo;
        }).collect(Collectors.toList());
        List<RegionListVo> collect1 = collect.stream().filter(regionListVo -> regionListVo.getParentId() == 0).map(regionListVo -> {
            List<RegionListVo> children = getChildren(collect, regionListVo);
            if (children.size() > 0) {
                regionListVo.setChildren(children);
            } else {
                regionListVo.setChildren(null);
            }
            return regionListVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("items", collect1);
    }

    @Override
    public CommonResult getRegionIds(Integer id) {

        if (id == null) {
            return CommonResult.error().message("请选择要删除的数据");
        }
        ArrayList<Integer> ids = new ArrayList<>();
        Region region = baseMapper.selectById(id);
        while (region.getLevel() != 1) {
            region = baseMapper.selectById(region.getParentId());
            ids.add(0, region.getId());
        }
        Integer[] integers = ids.stream().toArray(Integer[]::new);
        return CommonResult.ok().data("items", integers);
    }

    @Override
    public CommonResult addRegion(Region region) {

        if (region == null) {
            return CommonResult.error().message("请选择要添加的数据");
        }
        Region region1 = baseMapper.selectById(region.getParentId());
        if (region1 != null) {
            region.setLevel(region1.getLevel() + 1);
        } else {
            region.setLevel(1);
            region.setParentId(0);
        }
        int insert = baseMapper.insert(region);
        if (insert == 0) {
            return CommonResult.error().message("添加失败");
        }
        return CommonResult.ok().message("添加成功");

    }

    @Override
    public CommonResult updateRegionById(Integer id, Region region) {
        if (id == null) {
            return CommonResult.error().message("请选择要修改的数据");
        }
        region.setId(id);
        Region region1 = baseMapper.selectById(region.getParentId());
        if (region1 == null) {
            region.setLevel(1);
            region.setParentId(0);
        } else {
            region.setLevel(region1.getLevel() + 1);
        }
        int i = baseMapper.updateById(region);
        if (i == 0) {
            return CommonResult.error().message("修改失败");
        }
        return CommonResult.ok().message("修改成功");
    }

    @Override
    public CommonResult getRegionAll() {
        List<Region> regions = baseMapper.selectList(null);
        List<RegionListVo> collect = regions.stream().map(region -> {
            RegionListVo regionListVo = new RegionListVo();
            BeanUtils.copyProperties(region, regionListVo);
            return regionListVo;
        }).collect(Collectors.toList());
        List<RegionListVo> collect1 = collect.stream().filter(regionListVo -> regionListVo.getParentId() == 0).map(regionListVo -> {
            List<RegionListVo> children = getChildren(collect, regionListVo);
            if (children.size() > 0) {
                regionListVo.setChildren(children);
            } else {
                regionListVo.setChildren(null);
            }
            return regionListVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("items", collect1);
    }

    @Override
    public Integer[] getAllRegionIds(Integer regionId) {
        ArrayList<Integer> list = new ArrayList<>();
        Region region = baseMapper.selectById(regionId);
        list.add(region.getId());
        while (region.getLevel() != 1) {
            region = baseMapper.selectById(region.getParentId());
            list.add(0, region.getId());
        }
        Integer[] integers = list.stream().toArray(Integer[]::new);
        return integers;
    }

    private List<RegionListVo> getChildren(List<RegionListVo> collect, RegionListVo regionListVo) {

        List<RegionListVo> children = collect.stream().filter(e -> e.getParentId().equals(regionListVo.getId())).map(e -> {
            List<RegionListVo> children1 = getChildren(collect, e);
            if (children1.size() == 0) {
                e.setChildren(null);
            } else {
                e.setChildren(children1);
            }
            return e;
        }).collect(Collectors.toList());
        return children;
    }

    public String getName(Region region, HashMap<Integer, Region> map) {
        String name = region.getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        //倒转装名字
        while (region.getLevel() != 1) {
            region = map.get(region.getParentId());
            stringBuilder.insert(0, region.getName());
        }
        return stringBuilder.toString();
    }

    @Override
    public String getRegionName(Integer id, HashMap<Integer, Region> map) {
        if (id == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Region region = map.get(id);
        if (region == null) {
            return "";
        }
        //倒转装名字
        while (region != null) {
            stringBuilder.insert(0, region.getName());
            region = map.get(region.getParentId());
        }
        return stringBuilder.toString();
    }

    @Override
    public CommonResult getStoreRegionAll() {
        List<Store> stores = storeMapper.selectList(null);
        List<Integer> regionIds = stores.stream().map(store -> store.getRegionId()).collect(Collectors.toList());
        List<RegionListVo> regionListVos = getRegionAll(regionIds);
        return CommonResult.ok().data("items", regionListVos);

    }

    @Override
    public CommonResult getWarehouseRegionAll() {
        List<Warehouse> warehouses = warehouseMapper.selectList(null);
        List<Integer> regionIds = warehouses.stream().map(warehouse -> warehouse.getRegionId()).collect(Collectors.toList());
        List<RegionListVo> regionListVos = getRegionAll(regionIds);
        return CommonResult.ok().data("items", regionListVos);
    }

    @Override
    public CommonResult getBusinessStoreRegionAll() {
        List<Store> stores = storeMapper.selectList(new QueryWrapper<Store>().eq("status", "1"));
        List<Integer> regionIds = stores.stream().map(store -> store.getRegionId()).collect(Collectors.toList());
        List<RegionListVo> regionListVos = getRegionAll(regionIds);
        return CommonResult.ok().data("items", regionListVos);
    }

    private List<RegionListVo> getRegionAll(List<Integer> regionIds) {
        List<Region> regions = baseMapper.selectList(null);
        Map<Integer, RegionListVo> collect = regions.stream().map(region -> {
            RegionListVo regionListVo = new RegionListVo();
            BeanUtils.copyProperties(region, regionListVo);
            return regionListVo;
        }).collect(Collectors.toMap(RegionListVo::getId, regionListVo -> regionListVo));
        HashSet<RegionListVo> set1 = new HashSet<>();
        HashSet<RegionListVo> set2 = new HashSet<>();
        HashSet<RegionListVo> set3 = new HashSet<>();
        HashSet<RegionListVo> set4 = new HashSet<>();
        HashSet<RegionListVo> set5 = new HashSet<>();
        for (Integer regionId : regionIds) {
            Integer[] ids = getAllRegionIds(regionId);
            for (int j = 0; j < ids.length; j++) {
                RegionListVo regionListVo = collect.get(ids[j]);
                if (j == 0) {
                    set1.add(regionListVo);
                } else if (j == 1) {
                    set2.add(regionListVo);
                } else if (j == 2) {
                    set3.add(regionListVo);
                } else if (j == 3) {
                    set4.add(regionListVo);
                } else if (j == 4) {
                    set5.add(regionListVo);
                }
            }
        }
        List<RegionListVo> regionListVo = new ArrayList<>(set1);
        for (RegionListVo regionListVo1 : regionListVo) {
            List<RegionListVo> regionListVos = new ArrayList<>(set2);
            List<RegionListVo> collect1 = regionListVos.stream().filter(regionListVo2 -> regionListVo2.getParentId().equals(regionListVo1.getId())).collect(Collectors.toList());
            if (collect1.size() == 0) {
                regionListVo1.setChildren(null);
            } else {
                regionListVo1.setChildren(collect1);
            }
            for (RegionListVo regionListVo2 : collect1) {
                List<RegionListVo> regionListVos1 = new ArrayList<>(set3);
                List<RegionListVo> collect2 = regionListVos1.stream().filter(regionListVo3 -> regionListVo3.getParentId().equals(regionListVo2.getId())).collect(Collectors.toList());
                if (collect2.size() == 0) {
                    regionListVo2.setChildren(null);
                } else {
                    regionListVo2.setChildren(collect2);
                }
                for (RegionListVo regionListVo3 : collect2) {
                    List<RegionListVo> regionListVos2 = new ArrayList<>(set4);
                    List<RegionListVo> collect3 = regionListVos2.stream().filter(regionListVo4 -> regionListVo4.getParentId().equals(regionListVo3.getId())).collect(Collectors.toList());
                    if (collect3.size() == 0) {
                        regionListVo3.setChildren(null);
                    } else {
                        regionListVo3.setChildren(collect3);
                    }

                    for (RegionListVo regionListVo4 : collect3) {
                        List<RegionListVo> regionListVos3 = new ArrayList<>(set5);
                        List<RegionListVo> collect4 = regionListVos3.stream().filter(regionListVo5 -> regionListVo5.getParentId().equals(regionListVo4.getId())).collect(Collectors.toList());
                        if (collect4.size() == 0) {
                            regionListVo4.setChildren(null);
                        } else {
                            regionListVo4.setChildren(collect4);
                        }
                        regionListVo4.setChildren(collect4);
                    }
                }
            }
        }
        return regionListVo;
    }
}








