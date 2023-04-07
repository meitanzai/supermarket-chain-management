package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Instock;
import com.cqupt.th.supermarket.service.InstockService;
import com.cqupt.th.supermarket.mapper.InstockMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【instock】的数据库操作Service实现
* @createDate 2023-04-07 12:48:16
*/
@Service("instockService")
public class InstockServiceImpl extends ServiceImpl<InstockMapper, Instock>
    implements InstockService{

}




