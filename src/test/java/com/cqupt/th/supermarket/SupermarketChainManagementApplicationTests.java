package com.cqupt.th.supermarket;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.th.supermarket.constants.PurchaseOrderConstant;
import com.cqupt.th.supermarket.entity.*;
import com.cqupt.th.supermarket.mapper.*;
import com.cqupt.th.supermarket.service.OutstockService;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@SpringBootTest
class SupermarketChainManagementApplicationTests {
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;
    @Resource
    private ProductMapper productMapper;
    @Autowired
    @Qualifier("outstockService")
    private OutstockService outstockService;

    @Test
    void contextLoads() {
        List<Outstock> outstockList = outstockService.selectSumRecent7Days();
        outstockList.stream().forEach(System.out::println);
    }


}
