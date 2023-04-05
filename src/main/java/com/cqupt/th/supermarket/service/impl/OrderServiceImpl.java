package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Order;
import com.cqupt.th.supermarket.service.OrderService;
import com.cqupt.th.supermarket.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-04-05 21:59:01
*/
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




