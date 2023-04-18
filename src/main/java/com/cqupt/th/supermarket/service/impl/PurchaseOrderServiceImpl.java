package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.constants.PurchaseOrderConstant;
import com.cqupt.th.supermarket.entity.Purchase;
import com.cqupt.th.supermarket.entity.PurchaseOrder;
import com.cqupt.th.supermarket.mapper.PurchaseMapper;
import com.cqupt.th.supermarket.mapper.SupplierMapper;
import com.cqupt.th.supermarket.query.PurchaseOrderQuery;
import com.cqupt.th.supermarket.service.PurchaseOrderService;
import com.cqupt.th.supermarket.mapper.PurchaseOrderMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.PurchaseOrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【purchase_order】的数据库操作Service实现
 * @createDate 2023-04-06 13:56:35
 */
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder>
        implements PurchaseOrderService {
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private PurchaseMapper purchaseMapper;

    @Override
    public CommonResult getPurchaseOrderListPage(int currentPage, int pageSize, PurchaseOrderQuery purchaseOrderQuery) {
        if (currentPage <= 0 || pageSize <= 0) {
            return CommonResult.error().message("参数错误");
        }
        QueryWrapper<PurchaseOrder> purchaseOrderQueryWrapper = new QueryWrapper<>();
        purchaseOrderQueryWrapper.orderByDesc("gmt_modified");
        if (purchaseOrderQuery != null) {
            if (purchaseOrderQuery.getOrderNumber() != null) {
                purchaseOrderQueryWrapper.eq("order_number", purchaseOrderQuery.getOrderNumber());
            }
            if (purchaseOrderQuery.getSupplierId() != null) {
                purchaseOrderQueryWrapper.eq("supplier_id", purchaseOrderQuery.getSupplierId());
            }
            if (purchaseOrderQuery.getTotalPrice() != null) {
                purchaseOrderQueryWrapper.eq("total_price", purchaseOrderQuery.getTotalPrice());
            }
            if (purchaseOrderQuery.getIsPay() != null) {
                purchaseOrderQueryWrapper.eq("is_pay", purchaseOrderQuery.getIsPay());
            }
            if (purchaseOrderQuery.getStartTime() != null) {
                purchaseOrderQueryWrapper.ge("gmt_create", purchaseOrderQuery.getStartTime());
            }
            if (purchaseOrderQuery.getEndTime() != null) {
                purchaseOrderQueryWrapper.le("gmt_create", purchaseOrderQuery.getEndTime());
            }
        }
        Page<PurchaseOrder> purchaseOrderPage = new Page<>(currentPage, pageSize);
        this.page(purchaseOrderPage, purchaseOrderQueryWrapper);
        long total = purchaseOrderPage.getTotal();
        List<PurchaseOrder> records = purchaseOrderPage.getRecords();
        HashMap<Integer, String> supplierHashMap = new HashMap<>();
        supplierMapper.selectList(null).stream().forEach(supplier -> supplierHashMap.put(supplier.getId(), supplier.getName()));
        List<PurchaseOrderVo> rows = records.stream().map(r -> {
            PurchaseOrderVo purchaseOrderVo = new PurchaseOrderVo();
            BeanUtils.copyProperties(r, purchaseOrderVo);
            purchaseOrderVo.setSupplierName(supplierHashMap.get(r.getSupplierId()));
            return purchaseOrderVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", rows);

    }

    @Override
    public CommonResult updatePurchaseOrder(Integer id, PurchaseOrder purchaseOrder) {
        if (id == null || purchaseOrder == null) {
            return CommonResult.error().message("参数错误");
        }
        purchaseOrder.setId(id);

        if (PurchaseOrderConstant.IS_CANCEL.getCode().equals(purchaseOrder.getIsPay())) {
            purchaseMapper.updateTypeByPurchaseOrderId(purchaseOrder.getPurchaseId());
        } else {
            PurchaseOrder purchaseOrder1 = baseMapper.selectById(id);
            if (PurchaseOrderConstant.IS_CANCEL.getCode().equals(purchaseOrder1.getIsPay())) {
                purchaseMapper.updateTypeTo0ByPurchaseOrderId(purchaseOrder.getPurchaseId());
            }
        }
        int result = baseMapper.updateById(purchaseOrder);
        if (result > 0) {
            return CommonResult.ok().message("修改成功");
        } else {
            return CommonResult.error().message("修改失败");
        }
    }

    @Override
    public CommonResult getPurchaseOrderIsPay(Integer purchaseId) {
        if (purchaseId == null) {
            return CommonResult.error().message("参数错误");
        }
        PurchaseOrder purchaseOrder = baseMapper.selectOne(new QueryWrapper<PurchaseOrder>().eq("purchase_id", purchaseId));
        if (purchaseOrder == null) {
            return CommonResult.error().message("参数错误");
        }
        return CommonResult.ok().data("item", purchaseOrder.getIsPay());
    }

    @Override
    public CommonResult getPurchaseOrder(Integer purchaseId) {

        if (purchaseId == null) {
            return CommonResult.error().message("参数错误");
        }
        PurchaseOrder purchaseOrder = baseMapper.selectOne(new QueryWrapper<PurchaseOrder>().eq("purchase_id", purchaseId));
        return CommonResult.ok().data("item", purchaseOrder);

    }

    @Override
    public CommonResult getPurchaseOrderList() {
        List<PurchaseOrder> purchaseOrders = baseMapper.selectList(new QueryWrapper<PurchaseOrder>().orderByDesc("gmt_modified").eq("is_pay", 2));
        List<Object> collect = purchaseOrders.stream().map(p -> {
            PurchaseOrderVo purchaseOrderVo = new PurchaseOrderVo();
            BeanUtils.copyProperties(p, purchaseOrderVo);
            purchaseOrderVo.setSupplierName(supplierMapper.selectById(p.getSupplierId()).getName());
            return purchaseOrderVo;
        }).collect(Collectors.toList());

        return CommonResult.ok().data("items", collect);
    }
}




