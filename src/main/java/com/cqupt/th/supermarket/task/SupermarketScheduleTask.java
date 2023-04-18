package com.cqupt.th.supermarket.task;

import java.util.Date;

import com.cqupt.th.supermarket.entity.IncomeExpense;
import com.cqupt.th.supermarket.service.IncomeExpenseService;
import com.cqupt.th.supermarket.service.StatisticsService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
}
