package com.cqupt.th.supermarket.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Member;
import com.cqupt.th.supermarket.entity.MemberPoint;
import com.cqupt.th.supermarket.exception.SupermarketException;
import com.cqupt.th.supermarket.mapper.MemberMapper;
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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
    @Resource
    private MemberMapper memberMapper;

    @Override
    public CommonResult getMemberPointListPageByMemberId(Integer currentPage, Integer size, Integer memberId, MemberPointQuery memberPointQuery) {
        if (currentPage <= 0 || size <= 0) {
            return CommonResult.error().message("当前页码或每页记录数不能小于0");
        }
        if (memberId == null) {
            return CommonResult.error().message("会员id不能为空");
        }
        QueryWrapper<MemberPoint> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");
        queryWrapper.eq("member_id", memberId);
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
        if (currentPage <= 0 || size <= 0) {
            return CommonResult.error().message("当前页码或每页记录数不能小于0");
        }
        QueryWrapper<MemberPoint> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");
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
        queryWrapper.orderByDesc("gmt_modified");
        Page<MemberPoint> memberPointPage = new Page<>(currentPage, size);
        baseMapper.selectPage(memberPointPage, queryWrapper);
        long total = memberPointPage.getTotal();
        List<MemberPoint> memberPointList = memberPointPage.getRecords();
        List<Member> members = memberMapper.selectList(null);
        HashMap<Integer, Member> map = new HashMap<>(members.size());
        members.stream().forEach(member -> {
            map.put(member.getId(), member);
        });
        List<MemberPointVo> collect = memberPointList.stream().map(m -> {
            MemberPointVo memberPointVo = new MemberPointVo();
            BeanUtils.copyProperties(m, memberPointVo);
            if (map.get(m.getMemberId()) != null) {
                memberPointVo.setMemberName(map.get(m.getMemberId()).getName());
                memberPointVo.setCardNumber(map.get(m.getMemberId()).getCardNumber());
            }
            return memberPointVo;
        }).collect(Collectors.toList());
        return CommonResult.ok().data("rows", collect).data("total", total);
    }

    @Override
    public CommonResult batchDelete(Integer[] ids) {

        if (ids == null || ids.length == 0) {
            return CommonResult.error().message("请选择要删除的数据");
        }
        List<Integer> integers = Arrays.asList(ids);
        baseMapper.selectBatchIds(integers).stream().forEach(memberPoint -> {
            Integer memberId = memberPoint.getMemberId();
            Integer point = memberPoint.getPoint();
            memberMapper.updateMemberPointById(memberId, -point);
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
        memberMapper.updateMemberPointById(memberId, -point);
        int result = baseMapper.deleteById(id);
        if (result > 0) {
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
        memberMapper.updateMemberPointById(memberId, point);
        int insert = baseMapper.insert(memberPoint);
        if (insert > 0) {
            return CommonResult.ok().message("添加成功");
        }
        return CommonResult.error().message("添加失败");
    }

    @Override
    public CommonResult updateMemberPointById(Integer id, MemberPoint memberPoint) {

        if (id == null || memberPoint == null) {
            return CommonResult.error().message("参数不能为空");
        }
        Integer newMemberId = memberPoint.getMemberId();
        Integer newPoint = memberPoint.getPoint();
        MemberPoint memberPoint1 = baseMapper.selectById(id);
        if (memberPoint1 == null) {
            return CommonResult.error().message("数据不存在");
        }
        Integer oldMemberId = memberPoint1.getMemberId();
        if (newMemberId.equals(oldMemberId)) {
            Integer oldPoint = memberPoint1.getPoint();
            memberMapper.updateMemberPointById(newMemberId, newPoint - oldPoint);
        } else {
            memberMapper.updateMemberPointById(oldMemberId, -memberPoint1.getPoint());
            memberMapper.updateMemberPointById(newMemberId, newPoint);
        }
        int i = baseMapper.updateById(memberPoint);
        if (i > 0) {
            return CommonResult.ok().message("修改成功");
        }
        return CommonResult.error().message("修改失败");

    }
}




