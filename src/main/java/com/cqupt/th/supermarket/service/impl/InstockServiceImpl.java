package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.*;
import com.cqupt.th.supermarket.mapper.*;
import com.cqupt.th.supermarket.query.InstockQuery;
import com.cqupt.th.supermarket.service.InstockService;
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
    @Resource
    private WarehouseMapper warehouseMapper;
    @Resource
    private InventoryMapper inventoryMapper;

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
            if (instockQuery.getWarehouseId() != null) {
                instockQueryWrapper.eq("warehouse_id", instockQuery.getWarehouseId());
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
            instockVo.setWarehouseRegion(regionService.getRegionName(getRegionIdByWarehouseId(r.getWarehouseId()), regionHashMap));
            instockVo.setSupplierName(supplierHashMap.get(r.getSupplierId()));
            instockVo.setFromWarehouseRegion(regionService.getRegionName(getRegionIdByWarehouseId(r.getFromWarehouseId()), regionHashMap));
            return instockVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", rows);
    }

    @Override
    public CommonResult getInstockRegionIds(Integer warehouseId) {
        if (warehouseId == null) {
            return CommonResult.error().message("参数错误");
        }
        Warehouse warehouse = warehouseMapper.selectById(warehouseId);
        int regionId = 0;
        if (warehouse != null) {

            regionId = warehouse.getRegionId();
        }
        if (regionId == 0) {
            return CommonResult.ok().data("items", new Integer[0]);
        }
        Integer[] ids = regionService.getAllRegionIds(regionId);
        if (ids == null) {
            return CommonResult.error().message("参数错误");
        }
        return CommonResult.ok().data("items", ids);
    }

    @Override
    public CommonResult getwarehouseIdByRegionId(Integer regionId) {
        if (regionId == null) {
            return CommonResult.error().message("参数错误");
        }
        Warehouse warehouse = warehouseMapper.selectOne(new QueryWrapper<Warehouse>().eq("region_id", regionId));
        int id = -1;
        if (warehouse != null) {
            id = warehouse.getId();
        }
        return CommonResult.ok().data("item", id);
    }

    @Override
    public CommonResult deleteInstockById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("参数错误");
        }
        //TODO 库存的删除
        Instock instock = baseMapper.selectById(id);
        if (instock == null) {
            return CommonResult.error().message("参数错误");
        }
        Inventory inventory = inventoryMapper.selectOne(new QueryWrapper<Inventory>().eq("warehouse_id", instock.getWarehouseId()).eq("product_id", instock.getProductId()));
        if (inventory == null) {
            return CommonResult.error().message("参数错误");
        }
        inventory.setInstockCount(inventory.getInstockCount() - instock.getInstockCount());
        inventory.setInventoryCount(inventory.getInventoryCount() - instock.getInstockCount());
        inventoryMapper.updateById(inventory);
        int i = baseMapper.deleteById(id);
        if (i == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult updateInstockById(Integer id, Instock instock) {

        if (id == null || instock == null) {
            return CommonResult.error().message("参数错误");
        }
        instock.setId(id);
        Instock instock1 = baseMapper.selectById(id);
        if (instock1 == null) {
            return CommonResult.error().message("参数错误");
        }
        Inventory inventory = inventoryMapper.selectOne(new QueryWrapper<Inventory>().eq("warehouse_id", instock1.getWarehouseId()).eq("product_id", instock1.getProductId()));
        if (inventory == null) {
            return CommonResult.error().message("参数错误");
        }
        if (instock.getProductId() == inventory.getProductId() && instock.getWarehouseId() == inventory.getWarehouseId()) {
            inventory.setInstockCount(inventory.getInstockCount() - instock1.getInstockCount() + instock.getInstockCount());
            inventory.setInventoryCount(inventory.getInventoryCount() - instock1.getInstockCount() + instock.getInstockCount());
            inventoryMapper.updateById(inventory);
        } else {
            inventory.setInstockCount(inventory.getInstockCount() - instock1.getInstockCount());
            inventory.setInventoryCount(inventory.getInventoryCount() - instock1.getInstockCount());
            inventoryMapper.updateById(inventory);
            Inventory inventory1 = new Inventory();
            BeanUtils.copyProperties(instock, inventory1);
            inventory1.setInventoryCount(inventory1.getInstockCount());
            inventory1.setId(null);
            inventoryMapper.insert(inventory1);
        }
        //TODO 库存的更新
        int i = baseMapper.updateById(instock);
        if (i == 0) {
            return CommonResult.error().message("更新失败");
        }
        return CommonResult.ok().message("更新成功");
    }

    @Override
    public CommonResult addInstock(Instock instock) {

        if (instock == null) {
            return CommonResult.error().message("参数错误");
        }
        //TODO 库存的添加
        Inventory inventory = inventoryMapper.selectOne(new QueryWrapper<Inventory>().eq("warehouse_id", instock.getWarehouseId()).eq("product_id", instock.getProductId()));
        if (inventory == null) {
            Inventory inventory1 = new Inventory();
            BeanUtils.copyProperties(instock, inventory1);
            inventory1.setInventoryCount(inventory1.getInstockCount());
            inventoryMapper.insert(inventory1);
        } else {
            inventory.setInstockCount(inventory.getInstockCount() + instock.getInstockCount());
            inventory.setInventoryCount(inventory.getInventoryCount() + instock.getInstockCount());
            inventoryMapper.updateById(inventory);
        }
        int i = baseMapper.insert(instock);
        if (i == 0) {
            return CommonResult.error().message("添加失败");
        }
        return CommonResult.ok().message("添加成功");
    }

    private Integer getRegionIdByWarehouseId(Integer warehouseId) {
        Warehouse warehouse = warehouseMapper.selectById(warehouseId);
        int regionId = 0;
        if (warehouse != null) {
            regionId = warehouse.getRegionId();
        }
        return regionId;
    }

}




