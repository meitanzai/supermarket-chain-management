package com.cqupt.th.supermarket;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.th.supermarket.constants.PurchaseOrderConstant;
import com.cqupt.th.supermarket.entity.*;
import com.cqupt.th.supermarket.mapper.*;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SupermarketChainManagementApplicationTests {
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;
    @Resource
    private ProductMapper productMapper;

    @Test
    void contextLoads() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectList(new QueryWrapper<PurchaseOrder>().ne("is_pay", PurchaseOrderConstant.IS_CANCEL.getCode()));
        List<Integer> ids = purchaseOrders.stream().map(PurchaseOrder::getPurchaseId).collect(Collectors.toList());
        List<Purchase> purchases = purchaseMapper.selectList(new QueryWrapper<Purchase>().in("id", ids).orderByDesc("gmt_modified"));
        List<Integer> productIds = purchases.stream().map(Purchase::getProductId).distinct().collect(Collectors.toList());
        List<JSONObject> menus = new ArrayList<>();

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
                menus.add(oneMeun);
            }
        }
        System.out.println(menus);
    }


}
