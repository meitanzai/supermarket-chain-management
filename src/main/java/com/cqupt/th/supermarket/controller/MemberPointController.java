package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.MemberPoint;
import com.cqupt.th.supermarket.query.MemberPointQuery;
import com.cqupt.th.supermarket.service.MemberPointService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/30 15:37
 */
@RestController
@PreAuthorize("hasAuthority('member:memberpoint:index')")
@RequestMapping("/memberPoint")
public class MemberPointController {
    @Autowired
    @Qualifier("memberPointService")
    private MemberPointService memberPointService;

    @PostMapping("getMemberPointListPage/{currentPage}/{size}/{id}")
    public CommonResult getMemberPointListPageByMemberId(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @PathVariable("id") Integer id, @RequestBody(required = false) MemberPointQuery memberPointQuery) {
        return memberPointService.getMemberPointListPageByMemberId(currentPage, size, id, memberPointQuery);
    }
    @PostMapping("getMemberPointListPage/{currentPage}/{size}")
    public CommonResult getMemberPointListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @RequestBody(required = false) MemberPointQuery memberPointQuery) {
        return memberPointService.getMemberPointListPage(currentPage, size, memberPointQuery);
    }
    @DeleteMapping("batch/{ids}")
    public CommonResult batchDelete(@PathVariable("ids") Integer[] ids) {
        return memberPointService.batchDelete(ids);
    }
    @DeleteMapping("{id}")
    public CommonResult deleteById(@PathVariable("id") Integer id) {
        return memberPointService.deleteById(id);
    }
    @PostMapping
    public CommonResult addMemberPoint(@RequestBody MemberPoint memberPoint) {
        return memberPointService.addMemberPoint(memberPoint);
    }
    @PutMapping("{id}")
    public CommonResult updateMemberPointById(@PathVariable("id") Integer id, @RequestBody MemberPoint memberPoint) {
        return memberPointService.updateMemberPointById(id, memberPoint);
    }
}
