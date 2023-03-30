package com.cqupt.th.supermarket.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.MemberPoint;
import com.cqupt.th.supermarket.exception.SupermarketException;
import com.cqupt.th.supermarket.query.MemberPointQuery;
import com.cqupt.th.supermarket.service.MemberPointService;
import com.cqupt.th.supermarket.mapper.MemberPointMapper;
import com.cqupt.th.supermarket.service.MemberService;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.MemberPointVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16075
 * @description 针对表【member_point】的数据库操作Service实现
 * @createDate 2023-03-30 15:36:17
 */
@Service("memberPointService")
public class MemberPointServiceImpl extends ServiceImpl<MemberPointMapper, MemberPoint>
        implements MemberPointService {
    @Autowired
    @Qualifier("memberService")
    private MemberService memberService;

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

    @Override
    public CommonResult getMemberPointListPage(Integer currentPage, Integer size, MemberPointQuery memberPointQuery) {
        QueryWrapper<MemberPoint> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (memberPointQuery != null) {
            if (memberPointQuery.getMemberId() != null) {
                queryWrapper.eq("member_id", memberPointQuery.getMemberId());
            }
            if (memberPointQuery.getPoint() != null) {
                queryWrapper.eq("point", memberPointQuery.getPoint());
            }
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
        HashMap<Integer, String> map = new HashMap<>();
        memberService.list().forEach(member -> {
            map.put(member.getId(), member.getName());
        });
        List<MemberPointVo> collect = memberPointList.stream().map(m -> {
            MemberPointVo memberPointVo = new MemberPointVo();
            BeanUtils.copyProperties(m, memberPointVo);
            memberPointVo.setMemberName(map.get(m.getMemberId()));
            return memberPointVo;
        }).collect(Collectors.toList());
        long total = memberPointPage.getTotal();
        return CommonResult.ok().data("rows", collect).data("total", total);
    }

    @Override
    public CommonResult batchDelete(Integer[] ids) {

        if (ids == null || ids.length == 0) {
            return CommonResult.error().message("请选择要删除的数据");
        }
        List<Integer> integers = Arrays.asList(ids);
        baseMapper.selectBatchIds(integers).forEach(memberPoint -> {
            Integer memberId = memberPoint.getMemberId();
            Integer point = memberPoint.getPoint();
            boolean b = memberService.updateMemberPoint(memberId, -point);
            if (!b) {
                throw new SupermarketException(500, "删除失败");
            }
        });
        int result = baseMapper.deleteBatchIds(integers);
        if (result > 0) {
            return CommonResult.ok().message("删除成功");
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult deleteById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("请选择要删除的数据");
        }
        MemberPoint memberPoint = baseMapper.selectById(id);
        if (memberPoint == null) {
            return CommonResult.error().message("数据不存在");
        }
        Integer memberId = memberPoint.getMemberId();
        Integer point = memberPoint.getPoint();
        boolean b = memberService.updateMemberPoint(memberId, -point);
        int result = baseMapper.deleteById(id);
        if (result > 0 && b) {
            return CommonResult.ok().message("删除成功");
        }
        return CommonResult.error().message("删除失败");
    }

    @Override
    public CommonResult addMemberPoint(MemberPoint memberPoint) {
        if (memberPoint == null) {
            return CommonResult.error().message("参数不能为空");
        }
        Integer memberId = memberPoint.getMemberId();
        Integer point = memberPoint.getPoint();
        boolean b = memberService.updateMemberPoint(memberId, point);
        int insert = baseMapper.insert(memberPoint);
        if (insert > 0 && b) {
            return CommonResult.ok().message("添加成功");
        }
        return CommonResult.error().message("添加失败");
    }

    @Override
    public CommonResult updateMemberPoint(Integer id, MemberPoint memberPoint) {

        if (id == null || memberPoint == null) {
            return CommonResult.error().message("参数不能为空");
        }
        if (id.equals(memberPoint.getId())) {
            Integer memberId = memberPoint.getMemberId();
            Integer newPoint = memberPoint.getPoint();
            MemberPoint memberPoint1 = baseMapper.selectById(id);
            Integer oldPoint = memberPoint1.getPoint();
            boolean b = memberService.updateMemberPoint(memberId, newPoint - oldPoint);
            int i = baseMapper.updateById(memberPoint);
            if (i > 0 && b) {
                return CommonResult.ok().message("修改成功");
            }
            return CommonResult.error().message("修改失败");
        }
        return CommonResult.error().message("参数不匹配");
    }
}




