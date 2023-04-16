package com.cqupt.th.supermarket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.th.supermarket.constants.PurchaseOrderConstant;
import com.cqupt.th.supermarket.entity.Product;
import com.cqupt.th.supermarket.entity.Purchase;
import com.cqupt.th.supermarket.entity.PurchaseOrder;
import com.cqupt.th.supermarket.mapper.ProductMapper;
import com.cqupt.th.supermarket.mapper.PurchaseMapper;
import com.cqupt.th.supermarket.mapper.PurchaseOrderMapper;
import com.cqupt.th.supermarket.mapper.SupplierMapper;
import com.cqupt.th.supermarket.service.StatisticsService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author th
 * @date 2023/4/16 16:57
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public CommonResult getPriceComparison(Product product) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectList(new QueryWrapper<PurchaseOrder>().ne("is_pay", PurchaseOrderConstant.IS_CANCEL.getCode()));
        List<Integer> ids = purchaseOrders.stream().map(PurchaseOrder::getPurchaseId).collect(Collectors.toList());
        QueryWrapper<Purchase> purchaseQueryWrapper = new QueryWrapper<Purchase>().in("id", ids).orderByDesc("gmt_modified");
        if (product != null) {
            if (product.getId() != null) {
                purchaseQueryWrapper.eq("product_id", product.getId());
            }
        }
        List<Purchase> purchases = purchaseMapper.selectList(purchaseQueryWrapper);
        List<Integer> productIds = purchases.stream().map(Purchase::getProductId).distinct().collect(Collectors.toList());
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (Integer productId : productIds) {
            Product product1 = productMapper.selectById(productId);
            JSONObject oneMeun = new JSONObject();
            oneMeun.put("name", product1.getName());
            oneMeun.put("type", "line");
            List<BigDecimal> data = new ArrayList<>();
            for (Purchase purchase : purchases) {
                if (purchase.getProductId().equals(productId)) {
                    data.add(purchase.getPurchasePrice());
                }
            }
            oneMeun.put("data", data);
            jsonObjects.add(oneMeun);

        }

        return CommonResult.ok().data("items", jsonObjects);
    }

    @Override
    public CommonResult getSupplierPriceChange(Product product) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectList(new QueryWrapper<PurchaseOrder>().ne("is_pay", PurchaseOrderConstant.IS_CANCEL.getCode()));
        List<Integer> ids = purchaseOrders.stream().map(PurchaseOrder::getPurchaseId).collect(Collectors.toList());
        QueryWrapper<Purchase> purchaseQueryWrapper = new QueryWrapper<Purchase>().in("id", ids).orderByDesc("gmt_modified");
        if (product != null) {
            if (product.getId() != null) {
                purchaseQueryWrapper.eq("product_id", product.getId());
            } else {
                //查询数据库第一条数据productMapper
                purchaseQueryWrapper.eq("product_id", productMapper.selectOne(new QueryWrapper<Product>().last("limit 1")));
            }
        }
        List<Purchase> purchases = purchaseMapper.selectList(purchaseQueryWrapper);
        List<Integer> supplierIds = purchases.stream().map(Purchase::getSupplierId).distinct().collect(Collectors.toList());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "进价");
        jsonObject.put("type", "bar");
        jsonObject.put("barWidth", "60%");
        List<BigDecimal> data = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        for (Integer supplierId : supplierIds) {
            list.add(supplierMapper.selectById(supplierId).getName());
            for (Purchase purchase : purchases) {
                if (purchase.getSupplierId().equals(supplierId)) {
                    data.add(purchase.getPurchasePrice());
                    break;
                }
            }
        }
        jsonObject.put("data", data);
        jsonObject.put("list", list);

        return CommonResult.ok().data("items", jsonObject);
    }
}
