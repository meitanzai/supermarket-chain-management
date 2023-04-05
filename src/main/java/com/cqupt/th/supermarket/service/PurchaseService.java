package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Purchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.PurchaseQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【purchase】的数据库操作Service
* @createDate 2023-04-05 16:27:30
*/
public interface PurchaseService extends IService<Purchase> {

    CommonResult getPurchaseListPage(Integer currentPage, Integer pageSize, PurchaseQuery purchaseQuery);
}
