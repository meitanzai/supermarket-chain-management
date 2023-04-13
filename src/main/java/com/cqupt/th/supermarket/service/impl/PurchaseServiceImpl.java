package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Product;
import com.cqupt.th.supermarket.entity.Purchase;
import com.cqupt.th.supermarket.entity.PurchaseOrder;
import com.cqupt.th.supermarket.entity.Supplier;
import com.cqupt.th.supermarket.mapper.ProductMapper;
import com.cqupt.th.supermarket.mapper.PurchaseOrderMapper;
import com.cqupt.th.supermarket.mapper.SupplierMapper;
import com.cqupt.th.supermarket.query.PurchaseQuery;
import com.cqupt.th.supermarket.service.PurchaseService;
import com.cqupt.th.supermarket.mapper.PurchaseMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.utils.SnowFlakeUtil;
import com.cqupt.th.supermarket.vo.PurchaseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【purchase】的数据库操作Service实现
 * @createDate 2023-04-05 16:27:30
 */
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase>
        implements PurchaseService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;


    @Override
    public CommonResult getPurchaseListPage(Integer currentPage, Integer pageSize, PurchaseQuery purchaseQuery) {
        if (currentPage == null || pageSize == null) {
            return CommonResult.error().message("分页参数不能为空");
        }
        QueryWrapper<Purchase> purchaseQueryWrapper = new QueryWrapper<>();
        purchaseQueryWrapper.orderByDesc("gmt_modified");
        if (purchaseQuery != null) {
            if (purchaseQuery.getProductId() != null) {
                purchaseQueryWrapper.eq("product_id", purchaseQuery.getProductId());
            }
            if (purchaseQuery.getProductId() == null && purchaseQuery.getProductName() != null) {
                List<Product> products = productMapper.selectList(new QueryWrapper<Product>().like("name", purchaseQuery.getProductName()));
                if (products.size() == 0) {
                    return CommonResult.ok().data("total", 0).data("rows", null);
                }
                List<Integer> productIds = products.stream().map(Product::getId).collect(Collectors.toList());
                purchaseQueryWrapper.in("product_id", productIds);
            }
            if (purchaseQuery.getPurchaseNumber() != null) {
                purchaseQueryWrapper.eq("purchase_number", purchaseQuery.getPurchaseNumber());
            }
            if (purchaseQuery.getSupplierId() != null) {
                purchaseQueryWrapper.eq("supplier_id", purchaseQuery.getSupplierId());
            }
            if (purchaseQuery.getTotalPrice() != null) {
                purchaseQueryWrapper.eq("total_price", purchaseQuery.getTotalPrice());
            }
            if (purchaseQuery.getStartTime() != null) {
                purchaseQueryWrapper.ge("gmt_create", purchaseQuery.getStartTime());
            }
            if (purchaseQuery.getEndTime() != null) {
                purchaseQueryWrapper.le("gmt_create", purchaseQuery.getEndTime());
            }
        }
        Page<Purchase> purchasePage = new Page<>(currentPage, pageSize);
        baseMapper.selectPage(purchasePage, purchaseQueryWrapper);
        long total = purchasePage.getTotal();
        List<Purchase> records = purchasePage.getRecords();
        List<Product> products = productMapper.selectList(null);
        HashMap<Integer, String> productMap = new HashMap<>();
        products.forEach(p -> {
            productMap.put(p.getId(), p.getName());
        });
        List<Supplier> suppliers = supplierMapper.selectList(null);
        HashMap<Integer, String> supplierMap = new HashMap<>();
        suppliers.forEach(s -> {
            supplierMap.put(s.getId(), s.getName());
        });
        List<PurchaseVo> rows = records.stream().map(r -> {
            PurchaseVo purchaseVo = new PurchaseVo();
            BeanUtils.copyProperties(r, purchaseVo);
            purchaseVo.setProductName(productMap.get(r.getProductId()));
            purchaseVo.setSupplierName(supplierMap.get(r.getSupplierId()));
            return purchaseVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("total", total).data("rows", rows);
    }

    @Override
    public CommonResult deletePurchase(Integer id) {
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }

        int result = purchaseOrderMapper.delete(new QueryWrapper<PurchaseOrder>().eq("purchase_id", id));
        int i = baseMapper.deleteById(id);
        if (i == 0 || result == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult addPurchase(Purchase purchase) {

        if (purchase == null) {
            return CommonResult.error().message("参数不能为空");
        }
        BigDecimal purchasePrice = purchase.getPurchasePrice();
        if (purchasePrice != null) {
            productMapper.updatePurchasePrice(purchase.getProductId(), purchasePrice);
        }
        //TODO 生成订单
        long nextId = new SnowFlakeUtil(1, 1).getNextId();
        purchase.setPurchaseNumber(nextId);
        int id = baseMapper.insert(purchase);
        if (id <= 0) {
            return CommonResult.error().message("添加失败");
        }
        PurchaseOrder order = new PurchaseOrder();

        order.setOrderNumber(nextId);
        order.setSupplierId(purchase.getSupplierId());
        order.setTotalPrice(purchase.getTotalPrice());
        order.setPurchaseId(purchase.getId());
        order.setIsPay(2);
        int insert1 = purchaseOrderMapper.insert(order);
        if (insert1 <= 0) {
            return CommonResult.error().message("添加失败");
        }
        return CommonResult.ok().message("添加成功");
    }

    @Override
    public CommonResult updatePurchase(Integer id, Purchase purchase) {
        if (id == null || purchase == null) {
            return CommonResult.error().message("参数不能为空");
        }
        BigDecimal purchasePrice = purchase.getPurchasePrice();
        if (purchasePrice != null) {
            productMapper.updatePurchasePrice(purchase.getProductId(), purchasePrice);
        }
        //TODO 修改订单
        PurchaseOrder order = purchaseOrderMapper.selectOne(new QueryWrapper<PurchaseOrder>().eq("order_number", purchase.getPurchaseNumber()));
        order.setSupplierId(purchase.getSupplierId());
        order.setTotalPrice(purchase.getTotalPrice());
        int result = purchaseOrderMapper.updateById(order);
        int i = baseMapper.updateById(purchase);
        if (i == 0 || result == 0) {
            return CommonResult.error().message("修改失败");
        }
        return CommonResult.ok().message("修改成功");
    }

    @Override
    public CommonResult getPurchaseByPurchaseId(Integer purchaseId) {

        if (purchaseId == null) {
            return CommonResult.error().message("参数不能为空");
        }
        Purchase purchase = baseMapper.selectById(purchaseId);
        PurchaseVo purchaseVo = new PurchaseVo();
        BeanUtils.copyProperties(purchase, purchaseVo);
        Product product = productMapper.selectById(purchase.getProductId());
        purchaseVo.setProductName(product.getName());
        Supplier supplier = supplierMapper.selectById(purchase.getSupplierId());
        purchaseVo.setSupplierName(supplier.getName());
        if (purchase == null) {
            return CommonResult.error().message("查询失败");
        }
        return CommonResult.ok().data("item", purchaseVo);
    }

    @Override
    public CommonResult getSupplierIdByPurchaseId(Integer purchaseId) {
        if (purchaseId == null) {
            return CommonResult.error().message("参数不能为空");
        }
        Purchase purchase = baseMapper.selectById(purchaseId);
        if (purchase == null) {
            return CommonResult.error().message("查询失败");
        }
        Integer supplierId = purchase.getSupplierId();
        if (supplierId == null) {
            return CommonResult.ok().data("item", null);
        }
        Supplier supplier = supplierMapper.selectById(supplierId);
        if (supplier == null) {
            return CommonResult.ok().data("item", null);
        }
        return CommonResult.ok().data("item", supplier.getId());
    }

    @Override
    public CommonResult getBrandByPurchaseId(Integer purchaseId) {
        if (purchaseId == null) {
            return CommonResult.error().message("参数不能为空");
        }
        Purchase purchase = baseMapper.selectById(purchaseId);
        if (purchase == null) {
            return CommonResult.error().message("查询失败");
        }
        if (purchase.getSupplierId() == 0) {
            return CommonResult.ok().data("item", null);
        } else {
            Supplier supplier = supplierMapper.selectById(purchase.getSupplierId());
            if (supplier == null) {
                return CommonResult.ok().data("item", null);
            }
            return CommonResult.ok().data("item", supplier.getId());
        }
    }
}




