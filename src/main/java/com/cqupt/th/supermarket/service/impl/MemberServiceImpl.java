package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.constants.MemberConstant;
import com.cqupt.th.supermarket.entity.Brand;
import com.cqupt.th.supermarket.entity.Member;
import com.cqupt.th.supermarket.entity.MemberPoint;
import com.cqupt.th.supermarket.mapper.MemberPointMapper;
import com.cqupt.th.supermarket.query.MemberQuery;
import com.cqupt.th.supermarket.service.MemberService;
import com.cqupt.th.supermarket.mapper.MemberMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import com.cqupt.th.supermarket.vo.MemberPointVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author 16075
 * @description 针对表【member】的数据库操作Service实现
 * @createDate 2023-03-30 15:36:17
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Resource
    private MemberPointMapper memberPointMapper;

    @Override
    public CommonResult getMemberListByPage(Integer currentPage, Integer size, MemberQuery memberQuery) {
        if (currentPage <= 0 || size <= 0) {
            return CommonResult.error().message("参数不合法");
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        if (memberQuery != null) {
            if (StringUtils.hasText(memberQuery.getCardNumber())) {
                memberQueryWrapper.like("card_number", memberQuery.getCardNumber());
            }
            if (StringUtils.hasText(memberQuery.getName())) {
                memberQueryWrapper.like("name", memberQuery.getName());
            }
            if (StringUtils.hasText(memberQuery.getTel())) {
                memberQueryWrapper.like("tel", memberQuery.getTel());
            }
            if (memberQuery.getStatus() != null) {
                memberQueryWrapper.eq("status", memberQuery.getStatus());
            }
            if (memberQuery.getStartTime() != null) {
                memberQueryWrapper.ge("gmt_create", memberQuery.getStartTime());
            }
            if (memberQuery.getEndTime() != null) {
                memberQueryWrapper.le("gmt_create", memberQuery.getEndTime());
            }
        }
        memberQueryWrapper.orderByDesc("gmt_modified");
        Page<Member> memberPage = new Page<>(currentPage, size);
        baseMapper.selectPage(memberPage, memberQueryWrapper);
        long total = memberPage.getTotal();
        List<Member> rows = memberPage.getRecords();
        return CommonResult.ok().data("total", total).data("rows", rows);
    }

    @Override
    public CommonResult deleteMemberById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        memberPointMapper.delete(new QueryWrapper<MemberPoint>().eq("member_id", id));
        int i = baseMapper.deleteById(id);
        if (i == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult deleteMemberByIds(Integer[] ids) {

        if (ids == null || ids.length == 0) {
            return CommonResult.error().message("ids不能为空");
        }
        memberPointMapper.delete(new QueryWrapper<MemberPoint>().in("member_id", ids));
        int i = baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (i == 0) {
            return CommonResult.error().message("删除失败");
        }
        return CommonResult.ok().message("删除成功");
    }

    @Override
    public CommonResult addMember(Member member) {

        if (member == null) {
            return CommonResult.error().message("member不能为空");
        }
        if (member.getStatus() == null) {
            member.setStatus(MemberConstant.NORMAL.getCode());
        }
        int i = baseMapper.insert(member);
        if (i == 0) {
            return CommonResult.error().message("添加失败");
        }
        return CommonResult.ok().message("添加成功");
    }

    @Override
    public CommonResult updateMemberById(Integer id, Member member) {
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        if (member == null) {
            return CommonResult.error().message("member不能为空");
        }
        member.setId(id);
        int i = baseMapper.updateById(member);
        if (i == 0) {
            return CommonResult.error().message("更新失败");
        }
        return CommonResult.ok().message("更新成功");
    }

    @Override
    public CommonResult getMemberList() {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>().orderByDesc("gmt_modified");
        List<Member> members = baseMapper.selectList(queryWrapper);
        return CommonResult.ok().data("items", members);
    }

    @Override
    public CommonResult isExistMemberName(Member member) {
        if (member == null) {
            return CommonResult.error().message("参数错误");
        }
        if (member.getId() != null) {
            Member member1 = baseMapper.selectById(member.getId());
            if (member1 == null) {
                return CommonResult.error().message("参数错误");
            }
            if (member1.getName().equals(member.getName())) {
                return CommonResult.ok().data("item", false);
            }
        }
        Member member1 = baseMapper.selectOne(new QueryWrapper<Member>().eq("name", member.getName()));
        if (member1 == null) {
            return CommonResult.ok().data("item", false);
        }
        return CommonResult.ok().data("item", true);
    }

}




