package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.MemberPoint;
import com.cqupt.th.supermarket.query.MemberPointQuery;
import com.cqupt.th.supermarket.service.MemberPointService;
import com.cqupt.th.supermarket.mapper.MemberPointMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 16075
 * @description 针对表【member_point】的数据库操作Service实现
 * @createDate 2023-03-30 15:36:17
 */
@Service("memberPointService")
public class MemberPointServiceImpl extends ServiceImpl<MemberPointMapper, MemberPoint>
        implements MemberPointService {

    @Override
    public CommonResult getMemberPointListPageByMemberId(Integer currentPage, Integer size, Integer memberId, MemberPointQuery memberPointQuery) {
        if (memberId == null) {
            return CommonResult.error().message("会员id不能为空");
        }
        QueryWrapper<MemberPoint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId);
        queryWrapper.orderByDesc("gmt_create");
        if (memberPointQuery != null) {
            if (memberPointQuery.getStartTime() != null) {
                queryWrapper.ge("gmt_create", memberPointQuery.getStartTime());
            }
            if (memberPointQuery.getEndTime() != null) {
                queryWrapper.le("gmt_create", memberPointQuery.getEndTime());
            }
        }
        Page<MemberPoint> memberPointPage = new Page<>(currentPage, size);
        baseMapper.selectPage(memberPointPage, queryWrapper);
        List<MemberPoint> memberPointList = memberPointPage.getRecords();
        long total = memberPointPage.getTotal();
        return CommonResult.ok().data("rows", memberPointList).data("total", total);
    }
}




