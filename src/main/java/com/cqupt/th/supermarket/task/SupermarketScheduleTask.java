package com.cqupt.th.supermarket.task;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.th.supermarket.entity.*;

import com.cqupt.th.supermarket.service.*;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.InventoryVo;
import com.cqupt.th.supermarket.websocket.WebSocketServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author th
 * @date 2023/4/18 16:48
 */
@Component
@EnableScheduling
public class SupermarketScheduleTask {

    @Autowired
    @Qualifier("statisticsService")
    private StatisticsService statisticsService;
    @Autowired
    private IncomeExpenseService incomeExpenseService;
    @Autowired
    @Qualifier("inventoryService")
    private InventoryService inventoryService;
    @Autowired
    @Qualifier("outstockService")
    private OutstockService outstockService;
    @Autowired
    @Qualifier("productService")
    private ProductService productService;
    @Autowired
    @Qualifier("warehouseService")
    private WarehouseService warehouseService;
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;
    @Autowired
    @Qualifier("userBrowsingHistoryService")
    private UserBrowsingHistoryService userBrowsingHistoryService;

    //每个月最后一天的23点执行
    @Scheduled(cron = "0 0 23 L * ?")
    public void saveIncomeExpense() {
        CommonResult result = statisticsService.getProportionOfExpenditure();
        BigDecimal total = (BigDecimal) result.getData().get("total");
        IncomeExpense incomeExpense = new IncomeExpense();
        incomeExpense.setAmount(total);
        incomeExpense.setType(0);
        incomeExpenseService.save(incomeExpense);
    }


    //每小时执行一次
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void getInventoryCountNotice() {
        //查询7天内不同商品的出库数
        CopyOnWriteArraySet<WebSocketServer> webSocketSet = WebSocketServer.getWebSocketSet();
        if (webSocketSet == null || webSocketSet.size() == 0) {
            return;
        }

        List<Outstock> outstockList = outstockService.selectSumRecent7Days();
        //查询warehouseId和productId相同的仓库的库存数小于outstockList的出库数的30%的库存
        ArrayList<Inventory> inventories = new ArrayList<>();
        for (Outstock outstock : outstockList) {
            QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("warehouse_id", outstock.getWarehouseId());
            queryWrapper.eq("product_id", outstock.getProductId());
            queryWrapper.le("inventory_count", outstock.getOutstockCount() * 0.3);
            Inventory one = inventoryService.getOne(queryWrapper);
            if (one != null) {
                inventories.add(one);
            }
        }
        List<InventoryVo> collect = null;
        if (inventories != null && inventories.size() > 0) {
            collect = inventories.stream().map(i -> {
                InventoryVo inventoryVo = new InventoryVo();
                BeanUtils.copyProperties(i, inventoryVo);
                Product product = productService.getOne(new QueryWrapper<Product>().eq("id", i.getProductId()));
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
        }
        //给websocktServer发送消息
        List<InventoryVo> message = collect;
        CopyOnWriteArraySet<WebSocketServer> webSocketSet1 = WebSocketServer.getWebSocketSet();
        if (webSocketSet1 == null || webSocketSet1.size() == 0) {
            return;
        }
        for (WebSocketServer webSocketServer : webSocketSet1) {
            Integer userId = webSocketServer.getUserId();

            UserBrowsingHistory one = userBrowsingHistoryService.getOne(new QueryWrapper<UserBrowsingHistory>().eq("user_id", userId).eq("type", 2).orderByDesc("gmt_create").last("limit 1"));
            if (one != null) {
                message = collect.stream().filter(i -> i.getGmtModified().getTime() > one.getGmtCreate().getTime()).collect(Collectors.toList());
            }
            try {
                webSocketServer.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }

    }

}
