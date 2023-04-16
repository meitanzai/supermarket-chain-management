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

    @Override
    public CommonResult priceComparison() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectList(new QueryWrapper<PurchaseOrder>().ne("is_pay", PurchaseOrderConstant.IS_CANCEL.getCode()));
        List<Integer> ids = purchaseOrders.stream().map(PurchaseOrder::getPurchaseId).collect(Collectors.toList());
        List<Purchase> purchases = purchaseMapper.selectList(new QueryWrapper<Purchase>().in("id", ids).orderByDesc("gmt_modified"));
        List<Integer> productIds = purchases.stream().map(Purchase::getProductId).distinct().collect(Collectors.toList());
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (Integer productId : productIds) {
            Product product = productMapper.selectById(productId);
            JSONObject oneMeun = new JSONObject();
            if (product != null) {
                oneMeun.put("name", product.getName());
                oneMeun.put("type", "line");
                oneMeun.put("stack", "进价");
                List<BigDecimal> data = new ArrayList<>();
                for (Purchase purchase : purchases) {
                    if (purchase.getProductId().equals(productId)) {
                        data.add(purchase.getPurchasePrice());
                    }
                }
                oneMeun.put("data", data);
                jsonObjects.add(oneMeun);
            }
        }

        return CommonResult.ok().data("items", jsonObjects);
    }
}
