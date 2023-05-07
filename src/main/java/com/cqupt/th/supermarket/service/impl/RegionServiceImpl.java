package com.cqupt.th.supermarket.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Brand;
import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.service.RegionService;
import com.cqupt.th.supermarket.mapper.RegionMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.RegionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 地区服务impl
 *
 * @author TianHong
 * @date 2023/04/13
 */
@Service("regionService")
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region>
        implements RegionService {

    @Override
    public CommonResult getRegionAll() {
        List<Region> regions = baseMapper.selectList(new QueryWrapper<Region>().orderByDesc("gmt_modified"));
        HashMap<Integer, Region> regionMap = new HashMap<>(regions.size());
        List<RegionVo> collect = regions.stream().map(region -> {
            regionMap.put(region.getId(), region);
            RegionVo regionVo = new RegionVo();
            BeanUtils.copyProperties(region, regionVo);
            return regionVo;
        }).collect(Collectors.toList());
        List<RegionVo> collect1 = collect.stream().map(r -> {
            r.setFullName(getRegionName(r.getId(), regionMap));
            return r;
        }).filter(regionListVo -> regionListVo.getParentId() == 0).map(regionListVo -> {
            List<RegionVo> children = getChildren(collect, regionListVo);
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
    public CommonResult deleteRegionById(Integer id) {

        if (id == null) {
            return CommonResult.error().message("请选择要删除的数据");
        }
        HashSet<Integer> set = new HashSet<>();
        //递归获得所有子节点
        Set<Integer> ids = getSonIds(id, set);
        int i = baseMapper.deleteBatchIds(ids);
        if (i > 0) {
            return CommonResult.ok().message("删除成功");
        }
        return CommonResult.error().message("删除失败");

    }

    @Override
    public CommonResult addRegion(Region region) {

        if (region == null) {
            return CommonResult.error().message("请选择要添加的数据");
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
        int i = baseMapper.updateById(region);
        if (i == 0) {
            return CommonResult.error().message("修改失败");
        }
        return CommonResult.ok().message("修改成功");
    }

    @Override
    public Integer[] getRegionIdsById(Integer id) {

        if (id == null || id == 0) {
            return new Integer[]{};
        }
        Region region = baseMapper.selectById(id);
        if (region == null) {
            return new Integer[]{};
        }
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id);
        while (region.getParentId() != 0) {
            region = baseMapper.selectById(region.getParentId());
            ids.add(0, region.getId());
        }
        Integer[] integers = ids.stream().toArray(Integer[]::new);
        return integers;
    }

    private List<RegionVo> getChildren(List<RegionVo> collect, RegionVo regionVo) {

        List<RegionVo> children = collect.stream().filter(e -> e.getParentId().equals(regionVo.getId())).map(e -> {
            List<RegionVo> children1 = getChildren(collect, e);
            if (children1 != null && children1.size() > 0) {
                e.setChildren(children1);
            } else {
                e.setChildren(null);
            }
            return e;
        }).collect(Collectors.toList());
        return children;
    }

    @Override
    public String getRegionName(Integer id, HashMap<Integer, Region> map) {
        if (id == null || id == 0) {
            return "";
        }
        Region region = map.get(id);
        if (region == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        //倒转装名字
        while (region != null) {
            stringBuilder.insert(0, region.getName());
            region = map.get(region.getParentId());
        }
        return stringBuilder.toString();
    }

    @Override
    public CommonResult isExistRegionName(Region region) {
        if (region == null) {
            return CommonResult.error().message("参数错误");
        }
        if (region.getId() != null) {
            Region region1 = baseMapper.selectById(region.getId());
            if (region1 == null) {
                return CommonResult.error().message("参数错误");
            }
            if (region1.getName().equals(region.getName())) {
                return CommonResult.ok().data("item", false);
            }
        }
        if (region.getParentId() == null) {
            region.setParentId(0);
        }
        Region region1 = baseMapper.selectOne(new QueryWrapper<Region>().eq("name", region.getName()).eq("parent_id", region.getParentId()));
        if (region1 == null) {
            return CommonResult.ok().data("item", false);
        }
        return CommonResult.ok().data("item", true);
    }


    private Set<Integer> getSonIds(Integer id, HashSet<Integer> set) {
        set.add(id);
        List<Region> regions = baseMapper.selectList(new QueryWrapper<Region>().eq("parent_id", id));
        if (regions != null && regions.size() > 0) {
            regions.stream().forEach(region -> {
                set.add(region.getId());
                getSonIds(region.getId(), set);
            });
        }
        return set;
    }

    @Override
    public List<RegionVo> getRegionAll(List<Integer> regionIds) {
        List<Region> regions = baseMapper.selectList(null);
        Map<Integer, RegionVo> collect = regions.stream().map(region -> {
            RegionVo regionVo = new RegionVo();
            BeanUtils.copyProperties(region, regionVo);
            return regionVo;
        }).collect(Collectors.toMap(RegionVo::getId, regionListVo -> regionListVo));
        HashSet<RegionVo> set1 = new HashSet<>();
        HashSet<RegionVo> set2 = new HashSet<>();
        HashSet<RegionVo> set3 = new HashSet<>();
        HashSet<RegionVo> set4 = new HashSet<>();
        HashSet<RegionVo> set5 = new HashSet<>();
        for (Integer regionId : regionIds) {
            Integer[] ids = getRegionIdsById(regionId);
            for (int j = 0; j < ids.length; j++) {
                RegionVo regionVo = collect.get(ids[j]);
                if (j == 0) {
                    set1.add(regionVo);
                } else if (j == 1) {
                    set2.add(regionVo);
                } else if (j == 2) {
                    set3.add(regionVo);
                } else if (j == 3) {
                    set4.add(regionVo);
                } else if (j == 4) {
                    set5.add(regionVo);
                }
            }
        }
        List<RegionVo> regionVo = new ArrayList<>(set1);
        for (RegionVo regionVo1 : regionVo) {
            List<RegionVo> regionVos = new ArrayList<>(set2);
            List<RegionVo> collect1 = regionVos.stream().filter(regionListVo2 -> regionListVo2.getParentId().equals(regionVo1.getId())).collect(Collectors.toList());
            if (collect1.size() == 0) {
                regionVo1.setChildren(null);
            } else {
                regionVo1.setChildren(collect1);
            }
            for (RegionVo regionVo2 : collect1) {
                List<RegionVo> regionVos1 = new ArrayList<>(set3);
                List<RegionVo> collect2 = regionVos1.stream().filter(regionListVo3 -> regionListVo3.getParentId().equals(regionVo2.getId())).collect(Collectors.toList());
                if (collect2.size() == 0) {
                    regionVo2.setChildren(null);
                } else {
                    regionVo2.setChildren(collect2);
                }
                for (RegionVo regionVo3 : collect2) {
                    List<RegionVo> regionVos2 = new ArrayList<>(set4);
                    List<RegionVo> collect3 = regionVos2.stream().filter(regionListVo4 -> regionListVo4.getParentId().equals(regionVo3.getId())).collect(Collectors.toList());
                    if (collect3.size() == 0) {
                        regionVo3.setChildren(null);
                    } else {
                        regionVo3.setChildren(collect3);
                    }

                    for (RegionVo regionVo4 : collect3) {
                        List<RegionVo> regionVos3 = new ArrayList<>(set5);
                        List<RegionVo> collect4 = regionVos3.stream().filter(regionListVo5 -> regionListVo5.getParentId().equals(regionVo4.getId())).collect(Collectors.toList());
                        if (collect4.size() == 0) {
                            regionVo4.setChildren(null);
                        } else {
                            regionVo4.setChildren(collect4);
                        }
                        regionVo4.setChildren(collect4);
                    }
                }
            }
        }
        return regionVo;
    }
}








