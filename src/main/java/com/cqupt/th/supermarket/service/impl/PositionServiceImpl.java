package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Position;
import com.cqupt.th.supermarket.service.PositionService;
import com.cqupt.th.supermarket.mapper.PositionMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【position】的数据库操作Service实现
* @createDate 2023-03-31 16:12:25
*/
@Service("positionService")
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position>
    implements PositionService{

}




