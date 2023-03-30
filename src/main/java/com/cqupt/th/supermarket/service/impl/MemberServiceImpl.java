package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Member;
import com.cqupt.th.supermarket.query.MemberQuery;
import com.cqupt.th.supermarket.service.MemberService;
import com.cqupt.th.supermarket.mapper.MemberMapper;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author 16075
 * @description 针对表【member】的数据库操作Service实现
 * @createDate 2023-03-30 15:36:17
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public CommonResult getMemberListByPage(Integer currentPage, Integer size, MemberQuery memberQuery) {
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
        Page<Member> memberPage = new Page<>(currentPage, size);
        baseMapper.selectPage(memberPage, memberQueryWrapper);
        long total = memberPage.getTotal();
        List<Member> rows = memberPage.getRecords();
        return CommonResult.ok().data("total", total).data("rows", rows);
    }

    @Override
    public CommonResult getMemberStatusAndSexById(Integer id) {

        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        Member member = baseMapper.selectById(id);
        if (member == null) {
            return CommonResult.error().message("该会员不存在");
        }
        return CommonResult.ok().data("item", member);
    }

    @Override
    public CommonResult deleteMemberById(Integer id) {
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
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
        int i = baseMapper.insert(member);
        if (i == 0) {
            return CommonResult.error().message("添加失败");
        }
        return CommonResult.ok().message("添加成功");
    }

    @Override
    public CommonResult updateMember(Integer id, Member member) {
        if (id == null) {
            return CommonResult.error().message("id不能为空");
        }
        if (member == null) {
            return CommonResult.error().message("member不能为空");
        }
        if (!id.equals(member.getId())) {
            return CommonResult.error().message("id和member.id不一致");
        }
        int i = baseMapper.updateById(member);
        if (i == 0) {
            return CommonResult.error().message("更新失败");
        }
        return CommonResult.ok().message("更新成功");
    }

    @Override
    public CommonResult getMemberList() {

        List<Member> members = baseMapper.selectList(null);
        return CommonResult.ok().data("items", members);
    }

    @Override
    public boolean updateMemberPoint(Integer memberId, Integer point) {

        Member member = baseMapper.selectById(memberId);
        if (member == null) {
            return false;
        }
        member.setPoint(point + member.getPoint());
        int i = baseMapper.updateById(member);
        if (i == 0) {
            return false;
        }
        return true;
    }

}




