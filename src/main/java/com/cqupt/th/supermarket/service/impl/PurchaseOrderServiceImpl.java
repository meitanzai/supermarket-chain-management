package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.PurchaseOrder;
import com.cqupt.th.supermarket.service.PurchaseOrderService;
import com.cqupt.th.supermarket.mapper.PurchaseOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【purchase_order】的数据库操作Service实现
* @createDate 2023-04-06 13:56:35
*/
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder>
    implements PurchaseOrderService{

}




