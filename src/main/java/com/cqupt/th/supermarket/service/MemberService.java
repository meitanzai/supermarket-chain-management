package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.MemberQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
* @author 16075
* @description 针对表【member】的数据库操作Service
* @createDate 2023-03-30 15:36:17
*/
public interface MemberService extends IService<Member> {

    CommonResult getMemberListByPage(Integer currentPage, Integer size, MemberQuery memberQuery);

    CommonResult deleteMemberById(Integer id);

    CommonResult deleteMemberByIds(Integer[] ids);

    CommonResult addMember(Member member);

    CommonResult updateMemberById(Integer id, Member member);

    CommonResult getMemberList();
}
