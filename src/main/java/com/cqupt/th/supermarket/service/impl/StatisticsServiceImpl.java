package com.cqupt.th.supermarket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.th.supermarket.constants.EmployeeConstant;
import com.cqupt.th.supermarket.constants.IncomeExpenseConstant;
import com.cqupt.th.supermarket.constants.PurchaseOrderConstant;
import com.cqupt.th.supermarket.entity.*;
import com.cqupt.th.supermarket.mapper.*;
import com.cqupt.th.supermarket.service.RegionService;
import com.cqupt.th.supermarket.service.StatisticsService;
import com.cqupt.th.supermarket.service.WarehouseService;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.InventoryVo;
import com.cqupt.th.supermarket.vo.PurchaseOrderVo;
import com.cqupt.th.supermarket.vo.PurchaseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    @Qualifier("warehouseService")
    private WarehouseService warehouseService;
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private IncomeExpenseMapper incomeExpenseMapper;
    @Resource
    private OutstockMapper outstockMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private UserBrowsingHistoryMapper userBrowsingHistoryMapper;
    @Resource
    private InventoryMapper inventoryMapper;

    @Override
    public CommonResult getPriceComparison(Product product) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectList(new QueryWrapper<PurchaseOrder>().ne("is_pay", PurchaseOrderConstant.IS_CANCEL.getCode()));
        List<Integer> ids = purchaseOrders.stream().map(PurchaseOrder::getPurchaseId).collect(Collectors.toList());
        if (ids == null || ids.size() == 0) {
            return CommonResult.ok();
        }
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
        if (ids == null || ids.size() == 0) {
            return CommonResult.ok();
        }
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
        //查找上月的收入
        IncomeExpense incomeExpense = incomeExpenseMapper.selectOne(new QueryWrapper<IncomeExpense>().last("where type = 1 and DATE_FORMAT(gmt_create,'%Y%m') = DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH),'%Y%m')"));
        return CommonResult.ok().data("items", incomeExpense == null ? null : incomeExpense.getAmount());
    }

    @Override
    public CommonResult getExpendse() {
        //查找上月的支出
        IncomeExpense incomeExpense = incomeExpenseMapper.selectOne(new QueryWrapper<IncomeExpense>().last("where type = 0 and DATE_FORMAT(gmt_create,'%Y%m') = DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH),'%Y%m')"));
        return CommonResult.ok().data("items", incomeExpense == null ? null : incomeExpense.getAmount());
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

    @Override
    public CommonResult getProportionOfExpenditure() {
        //计算在职员工的薪资总额
        List<Employee> employees = employeeMapper.selectList(new QueryWrapper<Employee>().eq("status", EmployeeConstant.ON_JOB.getCode()));
        BigDecimal sum = employees.stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
        //计算门店的租金总和
        List<Store> stores = storeMapper.selectList(null);
        BigDecimal sum1 = stores.stream().map(Store::getRent).reduce(BigDecimal.ZERO, BigDecimal::add);
        //计算仓库的租金总和
        List<Warehouse> warehouses = warehouseService.list(null);
        BigDecimal sum2 = warehouses.stream().map(Warehouse::getRent).reduce(BigDecimal.ZERO, BigDecimal::add);
        //计算上个月除了取消的订单的总金额
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectList(new QueryWrapper<PurchaseOrder>().last("where is_pay = 1 AND DATE_FORMAT(gmt_create,'%Y%m') = DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH),'%Y%m')"));
        BigDecimal sum3 = purchaseOrders.stream().map(PurchaseOrder::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        //其他
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "员工薪资");
        jsonObject.put("value", sum);
        jsonObjects.add(jsonObject);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "门店租金");
        jsonObject1.put("value", sum1);
        jsonObjects.add(jsonObject1);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "仓库租金");
        jsonObject2.put("value", sum2);
        jsonObjects.add(jsonObject2);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("name", "采购支出");
        jsonObject3.put("value", sum3);
        jsonObjects.add(jsonObject3);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("name", "其他");
        jsonObject4.put("value", new BigDecimal(15000));
        jsonObjects.add(jsonObject4);
        //总支出
        BigDecimal sum4 = sum.add(sum1).add(sum2).add(sum3).add(new BigDecimal(15000));
        return CommonResult.ok().data("items", jsonObjects).data("total", sum4);

    }

    @Override
    public CommonResult getShelflife(JSONObject query) {
        //获取进货purchase的shelf_life-now <= day的进货
        QueryWrapper<Purchase> purchaseQueryWrapper = new QueryWrapper<>();
        if (query != null) {
            if (query.get("startTime") != null && StringUtils.hasText(query.get("startTime").toString())) {
                purchaseQueryWrapper.ge("shelf_life", query.get("startTime"));
            }
            if (query.get("endTime") != null && StringUtils.hasText(query.get("endTime").toString())) {
                purchaseQueryWrapper.le("shelf_life", query.get("endTime"));
            }
        }
        purchaseQueryWrapper.eq("type", 0).orderByAsc("shelf_life");
        //获取未取消的订单
        List<Purchase> purchases = purchaseMapper.selectList(purchaseQueryWrapper);
        //获取进货的商品id
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        purchases.stream().forEach(purchase -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productId", purchase.getProductId());
            jsonObject.put("promotionalPrice", productMapper.selectById(purchase.getProductId()).getPromotionalPrice());
            jsonObject.put("name", productMapper.selectById(purchase.getProductId()).getName());
            jsonObject.put("sellPrice", productMapper.selectById(purchase.getProductId()).getSellPrice());
            jsonObject.put("shelfLife", purchase.getShelfLife());
            jsonObject.put("purchaseNumber", purchase.getPurchaseNumber());
            jsonObjects.add(jsonObject);
        });
        return CommonResult.ok().data("items", jsonObjects);

    }

    @Override
    public CommonResult getUserBrowse(Integer userId, Integer type) {
        UserBrowsingHistory userBrowsingHistory = new UserBrowsingHistory();
        userBrowsingHistory.setUserId(userId);
        userBrowsingHistory.setType(type);
        userBrowsingHistoryMapper.insert(userBrowsingHistory);
        return CommonResult.ok();
    }

    @Override
    public CommonResult getNewOrderNoticeNum(Integer userId) {
        //查找最新的浏览记录
        UserBrowsingHistory userBrowsingHistory = userBrowsingHistoryMapper.selectOne(new QueryWrapper<UserBrowsingHistory>().eq("user_id", userId).eq("type", 1).orderByDesc("gmt_create").last("limit 1"));
        //查找最新的订单
        List<PurchaseOrder> purchaseOrders = null;
        if (userBrowsingHistory != null) {
            purchaseOrders = purchaseOrderMapper.selectList(new QueryWrapper<PurchaseOrder>().gt("gmt_create", userBrowsingHistory.getGmtCreate()).orderByDesc("gmt_create"));
        } else {
            purchaseOrders = purchaseOrderMapper.selectList(new QueryWrapper<PurchaseOrder>().orderByDesc("gmt_create"));
        }
        if (purchaseOrders == null || purchaseOrders.size() == 0) {
            return CommonResult.ok().data("rows", purchaseOrders).data("total", 0);
        }
        List<PurchaseOrderVo> rows = purchaseOrders.stream().map(p -> {
            PurchaseOrderVo purchaseOrderVo = new PurchaseOrderVo();
            BeanUtils.copyProperties(p, purchaseOrderVo);
            Supplier supplier = supplierMapper.selectById(p.getSupplierId());
            if (supplier != null) {
                purchaseOrderVo.setSupplierName(supplier.getName());
            }
            return purchaseOrderVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("rows", rows).data("total", purchaseOrders.size());
    }

    @Override
    public CommonResult getNewShelfLifeNoticeNum(Integer userId) {
        //shelf_life还剩一个月的商品
        List<Purchase> purchases = purchaseMapper.selectListShelfLifeFor30();
        if (purchases == null || purchases.size() == 0) {
            return CommonResult.ok().data("rows", purchases).data("total", 0);
        }
        List<PurchaseVo> rows = purchases.stream().map(p -> {
            PurchaseVo purchaseVo = new PurchaseVo();
            BeanUtils.copyProperties(p, purchaseVo);
            Product product = productMapper.selectById(p.getProductId());
            if (product != null) {
                purchaseVo.setProductName(product.getName());
            }
            return purchaseVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("rows", rows).data("total", purchases.size());
    }

    @Override
    public CommonResult getNewInventoryCountNoticeNum(Integer userId) {
        List<Outstock> outstockList = outstockMapper.selectSumRecent7Days();
        //查询warehouseId和productId相同的仓库的库存数小于outstockList的出库数的30%的库存
        ArrayList<Inventory> inventories = new ArrayList<>();
        for (Outstock outstock : outstockList) {
            QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("warehouse_id", outstock.getWarehouseId());
            queryWrapper.eq("product_id", outstock.getProductId());
            queryWrapper.le("inventory_count", outstock.getOutstockCount() * 0.3);
            Inventory one = inventoryMapper.selectOne(queryWrapper);
            if (one != null) {
                inventories.add(one);
            }
        }
        List<InventoryVo> collect = null;
        UserBrowsingHistory userBrowsingHistory = userBrowsingHistoryMapper.selectOne(new QueryWrapper<UserBrowsingHistory>().eq("user_id", userId).eq("type", 2).orderByDesc("gmt_create").last("limit 1"));

        if (inventories != null && inventories.size() > 0) {
            collect = inventories.stream().map(i -> {
                InventoryVo inventoryVo = new InventoryVo();
                BeanUtils.copyProperties(i, inventoryVo);
                Product product = productMapper.selectOne(new QueryWrapper<Product>().eq("id", i.getProductId()));
                if (product != null) {
                    inventoryVo.setProductName(product.getName());
                }
                Warehouse warehouse = warehouseService.getOne(new QueryWrapper<Warehouse>().eq("id", i.getWarehouseId()));
                if (warehouse != null) {
                    CommonResult commonResult = warehouseService.warehouseRegionIdsByRegionId(warehouse.getRegionId());
                    Integer[] items = (Integer[]) commonResult.getData().get("items");
                    List<Region> regionList = regionService.listByIds(Arrays.asList(items));
                    //组装名字
                    String name = regionList.stream().map(Region::getName).collect(Collectors.joining("-"));
                    inventoryVo.setWarehouseRegion(name);
                }
                return inventoryVo;
            }).collect(Collectors.toList());
            if (userBrowsingHistory != null) {
                collect = collect.stream().filter(i -> i.getGmtModified().getTime() > userBrowsingHistory.getGmtCreate().getTime()).collect(Collectors.toList());
            }
        }
        return CommonResult.ok().data("rows", collect).data("total", collect == null ? 0 : collect.size());
    }
}
