package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.MemberPoint;
import com.cqupt.th.supermarket.service.MemberPointService;
import com.cqupt.th.supermarket.mapper.MemberPointMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【member_point】的数据库操作Service实现
* @createDate 2023-03-30 15:36:17
*/
@Service("memberPointService")
public class MemberPointServiceImpl extends ServiceImpl<MemberPointMapper, MemberPoint>
    implements MemberPointService{

}




