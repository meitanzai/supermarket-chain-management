package com.cqupt.th.supermarket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.th.supermarket.constants.IncomeExpenseConstant;
import com.cqupt.th.supermarket.constants.PurchaseOrderConstant;
import com.cqupt.th.supermarket.entity.*;
import com.cqupt.th.supermarket.mapper.*;
import com.cqupt.th.supermarket.service.StatisticsService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
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
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private WarehouseMapper warehouseMapper;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private IncomeExpenseMapper incomeExpenseMapper;
    @Resource
    private OutstockMapper outstockMapper;

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

    @Override
    public CommonResult getExpenditure() {

        return null;
    }

    @Override
    public CommonResult getMemberRegister() {
        //统计当月会员注册数量
        Long aLong = memberMapper.selectCount(new QueryWrapper<Member>().last("where DATE_FORMAT(gmt_create,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m')"));
        return CommonResult.ok().data("items", aLong);
    }

    @Override
    public CommonResult getOrders() {
        //统计当月订单数量,排除未支付订单is_pay=3
        Long aLong = purchaseOrderMapper.selectCount(new QueryWrapper<PurchaseOrder>().last("where is_pay <> 3 and DATE_FORMAT(gmt_create,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m')"));
        return CommonResult.ok().data("items", aLong);
    }

    @Override
    public CommonResult getIncome() {
        //查找当月的
        IncomeExpense incomeExpense = incomeExpenseMapper.selectOne(new QueryWrapper<IncomeExpense>().last("where type = 1 and DATE_FORMAT(gmt_create,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m')"));
        return CommonResult.ok().data("items", incomeExpense.getAmount());
    }

    @Override
    public CommonResult getExpendse() {
        //查找当月的
        IncomeExpense incomeExpense = incomeExpenseMapper.selectOne(new QueryWrapper<IncomeExpense>().last("where type = 0 and DATE_FORMAT(gmt_create,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m')"));
        return CommonResult.ok().data("items", incomeExpense.getAmount());
    }

    @Override
    public CommonResult getIncomeAndExpendse() {
        //查找每个月的收入和支出
        List<IncomeExpense> incomeExpenses = incomeExpenseMapper.selectList(new QueryWrapper<IncomeExpense>().orderByAsc("gmt_create"));
        ArrayList<BigDecimal> inCome = new ArrayList<>();
        ArrayList<BigDecimal> expendse = new ArrayList<>();
        incomeExpenses.stream().forEach(incomeExpense -> {
            if (incomeExpense.getType().equals(IncomeExpenseConstant.INCOME.getCode())) {
                inCome.add(incomeExpense.getAmount());
            } else {
                expendse.add(incomeExpense.getAmount());
            }
        });
        return CommonResult.ok().data("income", inCome).data("expendse", expendse);
    }

    @Override
    public CommonResult getDailySales() {
        //查找当天的出库
        List<Outstock> outstocks = outstockMapper.selectList(new QueryWrapper<Outstock>().last("where type = 1 AND DATE_FORMAT(gmt_create,'%Y%m%d') = DATE_FORMAT(CURDATE(),'%Y%m%d')"));
        //把商品id相同的数量相加
        Map<Integer, Integer> collect = outstocks.stream().collect(Collectors.groupingBy(Outstock::getProductId, Collectors.summingInt(Outstock::getOutstockCount)));
        //数量从大到小排序
        Map<Integer, Integer> collect1 = collect.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //取前十
        Map<Integer, Integer> collect2 = collect1.entrySet().stream().limit(10).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //取商品名
        ArrayList<String> list = new ArrayList<>();
        collect2.keySet().stream().forEach(integer -> {
            list.add(productMapper.selectById(integer).getName());
        });
        //取数量
        ArrayList<Integer> list1 = new ArrayList<>();
        collect2.values().stream().forEach(list1::add);
        return CommonResult.ok().data("name", list).data("data", list1);

    }
}
