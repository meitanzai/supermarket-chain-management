package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Inventory;
import com.cqupt.th.supermarket.service.InventoryService;
import com.cqupt.th.supermarket.mapper.InventoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【inventory】的数据库操作Service实现
* @createDate 2023-04-06 19:09:26
*/
@Service("inventoryService")
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory>
    implements InventoryService{

}




