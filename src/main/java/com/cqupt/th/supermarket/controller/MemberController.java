package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Member;
import com.cqupt.th.supermarket.query.MemberQuery;
import com.cqupt.th.supermarket.service.MemberService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/30 15:37
 */
@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {
    @Autowired
    @Qualifier("memberService")
    private MemberService memberService;

    @PostMapping("{currentPage}/{size}")
    public CommonResult getMemberListByPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @RequestBody(required = false) MemberQuery memberQuery) {
        return memberService.getMemberListByPage(currentPage, size, memberQuery);
    }
    @GetMapping("status/{id}")
    public CommonResult getMemberStatusAndSexById(@PathVariable("id") Integer id){
        return memberService.getMemberStatusAndSexById(id);
    }
    @DeleteMapping("{id}")
    public CommonResult deleteMemberById(@PathVariable("id") Integer id){
        return memberService.deleteMemberById(id);
    }
    @DeleteMapping("batch/{ids}")
    public CommonResult deleteMemberByIds(@PathVariable("ids") Integer[] ids){
        return memberService.deleteMemberByIds(ids);
    }
    @PostMapping
    public CommonResult addMember(@RequestBody Member member){
        return memberService.addMember(member);
    }
    @PutMapping("{id}")
    public CommonResult updateMember(@PathVariable("id") Integer id, @RequestBody Member member){
        return memberService.updateMember(id, member);
    }
}
