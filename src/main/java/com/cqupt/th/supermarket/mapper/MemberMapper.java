package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 16075
* @description 针对表【member】的数据库操作Mapper
* @createDate 2023-03-30 15:36:17
* @Entity com.cqupt.th.supermarket.entity.Member
*/
public interface MemberMapper extends BaseMapper<Member> {

    void updateMemberPointById(@Param("memberId") Integer memberId,@Param("point") int point);
}




