package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.service.StatisticsService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/priceComparison")
    public CommonResult priceComparison() {
        return statisticsService.priceComparison();
    }
}
