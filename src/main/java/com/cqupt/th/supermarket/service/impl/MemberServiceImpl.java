package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Member;
import com.cqupt.th.supermarket.service.MemberService;
import com.cqupt.th.supermarket.mapper.MemberMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【member】的数据库操作Service实现
* @createDate 2023-03-30 15:36:17
*/
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{

}




