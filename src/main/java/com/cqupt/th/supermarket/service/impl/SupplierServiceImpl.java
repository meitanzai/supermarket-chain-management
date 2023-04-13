package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.*;
import com.cqupt.th.supermarket.mapper.RegionMapper;
import com.cqupt.th.supermarket.query.SupplierQuery;
import com.cqupt.th.supermarket.service.RegionService;
import com.cqupt.th.supermarket.service.SupplierService;
import com.cqupt.th.supermarket.mapper.SupplierMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.StoreVo;
import com.cqupt.th.supermarket.vo.SupplierVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【supplier】的数据库操作Service实现
 * @createDate 2023-04-04 22:42:20
 */
@Service("supplierService")
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier>
        implements SupplierService {
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;

    @Override
    public CommonResult getSupplierListPage(int currentPage, int pageSize, SupplierQuery supplierQuery) {
        if (currentPage <= 0 || pageSize <= 0) {
            return CommonResult.error().message("参数错误");
        }
        QueryWrapper<Supplier> supplierQueryWrapper = new QueryWrapper<>();
        List<Region> regionList = regionService.list(null);
        HashMap<Integer, Region> map = new HashMap<>();
        regionList.stream().forEach(r -> {
            map.put(r.getId(), r);
        });
        supplierQueryWrapper.orderByDesc("gmt_modified");
        if (supplierQuery != null) {
            if (supplierQuery.getRegionId() != null) {
                supplierQueryWrapper.eq("region_id", supplierQuery.getRegionId());
            }
            if (StringUtils.hasText(supplierQuery.getName())) {
                supplierQueryWrapper.like("name", supplierQuery.getName());
            }
            if (StringUtils.hasText(supplierQuery.getContactPerson())) {
                supplierQueryWrapper.like("contact_Person", supplierQuery.getContactPerson());
            }
            if (StringUtils.hasText(supplierQuery.getTel())) {
                supplierQueryWrapper.like("tel", supplierQuery.getTel());
            }
            if (supplierQuery.getStartTime() != null) {
                supplierQueryWrapper.ge("gmt_create", supplierQuery.getStartTime());
            }
            if (supplierQuery.getEndTime() != null) {
                supplierQueryWrapper.le("gmt_create", supplierQuery.getEndTime());
            }
        }
        Page<Supplier> supplierPage = new Page<>(currentPage, pageSize);
        baseMapper.selectPage(supplierPage, supplierQueryWrapper);
        long total = supplierPage.getTotal();
        List<Supplier> records = supplierPage.getRecords();
        List<SupplierVo> rows = records.stream().map(s -> {
            SupplierVo supplierVo = new SupplierVo();
            BeanUtils.copyProperties(s, supplierVo);
            supplierVo.setRegionName(regionService.getRegionName(s.getRegionId(), map));
            return supplierVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", rows);
    }

    @Override
    public CommonResult deleteSupplierBatch(Integer[] ids) {

        if (ids == null || ids.length == 0) {
            return CommonResult.error().message("参数错误");
        }
        int result = baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (result == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult updateSupplierIsUse(Integer id, Integer isUse) {

        if (id == null || isUse == null) {
            return CommonResult.error().message("参数错误");
        }
        Supplier supplier = baseMapper.selectById(id);
        if (supplier == null) {
            return CommonResult.error().message("参数错误");
        }
        supplier.setIsUse(isUse);
        int result = baseMapper.updateById(supplier);
        if (result == 0) {
            return CommonResult.error().message("修改失败");
        }
        return CommonResult.ok().message("修改成功");
    }

    @Override
    public CommonResult getSupplierRegionIds(Integer regionId) {
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
    public CommonResult deleteSupplier(Integer id) {

        if (id == null) {
            return CommonResult.error().message("参数错误");
        }
        int result = baseMapper.deleteById(id);
        if (result == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult addSupplier(Supplier supplier) {

        if (supplier == null) {
            return CommonResult.error().message("参数错误");
        }
        int result = baseMapper.insert(supplier);
        if (result == 0) {
            return CommonResult.error().message("添加失败");
        }
        return CommonResult.ok().message("添加成功");
    }

    @Override
    public CommonResult updateSupplier(Integer id, Supplier supplier) {

        if (id == null || supplier == null) {
            return CommonResult.error().message("参数错误");
        }
        supplier.setId(id);
        int result = baseMapper.updateById(supplier);
        if (result == 0) {
            return CommonResult.error().message("修改失败");
        }
        return CommonResult.ok().message("修改成功");
    }

    @Override
    public CommonResult getAllSupplier() {

        List<Supplier> suppliers = baseMapper.selectList(null);
        return CommonResult.ok().data("items", suppliers);
    }

    @Override
    public CommonResult getSupplierByName(Supplier supplier) {
        if (supplier == null) {
            return CommonResult.error().message("参数错误");
        }
        if (supplier.getId() != null) {
            Supplier supplier1 = baseMapper.selectById(supplier.getId());
            if (supplier1 == null) {
                return CommonResult.error().message("参数错误");
            }
            if (supplier1.getName().equals(supplier.getName())) {
                return CommonResult.ok().data("item", null);
            }
        }
        Supplier supplier1 = baseMapper.selectOne(new QueryWrapper<Supplier>().eq("name", supplier.getName()));
        if (supplier1 == null) {
            return CommonResult.ok().data("item", null);
        }
        return CommonResult.ok().data("item", supplier1);
    }

    @Override
    public CommonResult getRegionByRegionId(Supplier supplier) {
        if (supplier == null) {
            return CommonResult.error().message("参数错误");
        }
        if (supplier.getRegionId() != null && supplier.getRegionId() == 0) {
            return CommonResult.ok().data("item", null);
        }
        if (supplier.getId() != null) {
            Supplier supplier1 = baseMapper.selectById(supplier.getId());
            if (supplier1 == null) {
                return CommonResult.error().message("参数错误");
            }
            if (supplier1.getRegionId().equals(supplier.getRegionId())) {
                return CommonResult.ok().data("item", null);
            }
        }
        Supplier supplier1 = baseMapper.selectOne(new QueryWrapper<Supplier>().eq("region_id", supplier.getRegionId()));
        if (supplier1 == null) {
            return CommonResult.ok().data("item", null);
        }
        return CommonResult.ok().data("item", supplier1);
    }

}




