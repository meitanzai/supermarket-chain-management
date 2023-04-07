package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Instock;
import com.cqupt.th.supermarket.entity.Product;
import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.mapper.ProductMapper;
import com.cqupt.th.supermarket.mapper.SupplierMapper;
import com.cqupt.th.supermarket.query.InstockQuery;
import com.cqupt.th.supermarket.service.InstockService;
import com.cqupt.th.supermarket.mapper.InstockMapper;
import com.cqupt.th.supermarket.service.RegionService;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.InstockVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【instock】的数据库操作Service实现
 * @createDate 2023-04-07 12:48:16
 */
@Service("instockService")
public class InstockServiceImpl extends ServiceImpl<InstockMapper, Instock>
        implements InstockService {
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public CommonResult getInstockListPage(Integer currentPage, Integer pageSize, InstockQuery instockQuery) {
        if (currentPage <= 0 || pageSize <= 0) {
            return CommonResult.error().message("参数错误");
        }
        QueryWrapper<Instock> instockQueryWrapper = new QueryWrapper<>();
        instockQueryWrapper.orderByDesc("gmt_modified");
        if (instockQueryWrapper != null) {
            if (instockQuery.getSupplierId() != null) {
                instockQueryWrapper.eq("supplier_id", instockQuery.getSupplierId());
            }
            if (instockQuery.getProductId() != null) {
                instockQueryWrapper.eq("product_id", instockQuery.getProductId());
            }
            if (instockQuery.getInstockCount() != null) {
                instockQueryWrapper.eq("instock_count", instockQuery.getInstockCount());
            }
            if (instockQuery.getFromWarehouseId() != null) {
                instockQueryWrapper.eq("from_warehouse_id", instockQuery.getFromWarehouseId());
            }
            if (instockQuery.getStartTime() != null) {
                instockQueryWrapper.ge("gmt_create", instockQuery.getStartTime());
            }
            if (instockQuery.getEndTime() != null) {
                instockQueryWrapper.le("gmt_create", instockQuery.getEndTime());
            }
        }
        Page<Instock> instockPage = new Page<>(currentPage, pageSize);
        baseMapper.selectPage(instockPage, instockQueryWrapper);
        long total = instockPage.getTotal();
        List<Instock> records = instockPage.getRecords();
        HashMap<Integer, String> productHashMap = new HashMap<>();
        productMapper.selectList(null).stream().forEach(product -> {
            productHashMap.put(product.getId(), product.getName());
        });
        HashMap<Integer, Region> regionHashMap = new HashMap<>();
        regionService.list(null).stream().forEach(r -> {
            regionHashMap.put(r.getId(), r);
        });
        HashMap<Integer, String> supplierHashMap = new HashMap<>();
        supplierMapper.selectList(null).stream().forEach(supplier -> {
            supplierHashMap.put(supplier.getId(), supplier.getName());
        });
        List<InstockVo> rows = records.stream().map(r -> {
            InstockVo instockVo = new InstockVo();
            BeanUtils.copyProperties(r, instockVo);
            instockVo.setProductName(productHashMap.get(r.getProductId()));
            instockVo.setWarehouseRegion(regionService.getRegionName(r.getWarehouseId(), regionHashMap));
            instockVo.setSupplierName(supplierHashMap.get(r.getSupplierId()));
            instockVo.setFromWarehouseRegion(regionService.getRegionName(r.getFromWarehouseId(), regionHashMap));
            return instockVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", rows);
    }
}




