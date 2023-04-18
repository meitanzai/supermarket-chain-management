package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Product;
import com.cqupt.th.supermarket.service.StatisticsService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/4/16 16:55
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    @Qualifier("statisticsService")
    private StatisticsService statisticsService;

    @PostMapping("/priceComparison")
    public CommonResult getPriceComparison(@RequestBody(required = false) Product product) {
        return statisticsService.getPriceComparison(product);
    }

    @PostMapping("supplierPriceChange")
    public CommonResult getSupplierPriceChange(@RequestBody(required = false) Product product) {
        return statisticsService.getSupplierPriceChange(product);
    }

    @GetMapping("memberRegister")
    public CommonResult getMemberRegister() {
        return statisticsService.getMemberRegister();
    }

    @GetMapping("orders")
    public CommonResult getOrders() {
        return statisticsService.getOrders();
    }

    @GetMapping("income")
    public CommonResult getIncome() {
        return statisticsService.getIncome();
    }
    @GetMapping("expendse")
    public CommonResult getExpendse() {
        return statisticsService.getExpendse();
    }
    @GetMapping("incomeAndExpendse")
    public CommonResult getIncomeAndExpendse() {
        return statisticsService.getIncomeAndExpendse();
    }
    @GetMapping("dailySales")
    public CommonResult getDailySales() {
        return statisticsService.getDailySales();
    }
    @GetMapping("proportionOfExpenditure")
    public CommonResult getProportionOfExpenditure() {
        return statisticsService.getProportionOfExpenditure();
    }
}
