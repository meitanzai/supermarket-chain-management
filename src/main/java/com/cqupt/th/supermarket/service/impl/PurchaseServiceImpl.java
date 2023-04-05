package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Purchase;
import com.cqupt.th.supermarket.service.PurchaseService;
import com.cqupt.th.supermarket.mapper.PurchaseMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【purchase】的数据库操作Service实现
* @createDate 2023-04-05 16:27:30
*/
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase>
    implements PurchaseService{

}




