package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.entity.Supplier;
import com.cqupt.th.supermarket.mapper.RegionMapper;
import com.cqupt.th.supermarket.query.SupplierQuery;
import com.cqupt.th.supermarket.service.SupplierService;
import com.cqupt.th.supermarket.mapper.SupplierMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 16075
 * @description 针对表【supplier】的数据库操作Service实现
 * @createDate 2023-04-04 22:42:20
 */
@Service("supplierService")
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier>
        implements SupplierService {
    @Resource
    private RegionMapper regionMapper;

    @Override
    public CommonResult getSupplierListPage(int currentPage, int pageSize, SupplierQuery supplierQuery) {
        if (currentPage <= 0 || pageSize <= 0) {
            return CommonResult.error().message("参数错误");
        }
        QueryWrapper<Supplier> supplierQueryWrapper = new QueryWrapper<>();
        List<Region> regionList = regionMapper.selectList(null);
        HashMap<Integer, Region> map = new HashMap<>();
        regionList.stream().forEach(r -> {
            map.put(r.getId(), r);
        });
        supplierQueryWrapper.orderByDesc("gmt_modified");
        if (supplierQuery != null) {
            if (supplierQuery.getRegionParentId() != null) {
                ArrayList<Integer> list1 = new ArrayList<>();
                map.forEach((k, v) -> {
                    if (v.getParentId().equals(supplierQuery.getRegionParentId())) {
                        list1.add(v.getId());
                    }
                });
                if (list1.size() != 0) {
                    supplierQueryWrapper.in("region_id", list1);
                } else {
                    return CommonResult.ok().data("total", 0).data("rows", new ArrayList<>());
                }
            }
            if (StringUtils.hasText(supplierQuery.getName())) {
                supplierQueryWrapper.like("name", supplierQuery.getName());
            }
            if (StringUtils.hasText(supplierQuery.getContactPerson())) {
                supplierQueryWrapper.like("contactPerson", supplierQuery.getContactPerson());
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
        List<Supplier> rows = supplierPage.getRecords();
        return CommonResult.ok().data("total", total).data("rows", rows);
    }
}




