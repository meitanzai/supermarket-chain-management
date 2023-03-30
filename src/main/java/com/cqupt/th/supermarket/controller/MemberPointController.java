package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.query.MemberPointQuery;
import com.cqupt.th.supermarket.service.MemberPointService;
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
@RequestMapping("/memberPoint")
public class MemberPointController {
    @Autowired
    @Qualifier("memberPointService")
    private MemberPointService memberPointService;

    @PostMapping("getMemberPointListPage/{currentPage}/{size}/{id}")
    public CommonResult getMemberPointListPageByMemberId(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @PathVariable("id") Integer id, @RequestBody(required = false) MemberPointQuery memberPointQuery) {
        return memberPointService.getMemberPointListPageByMemberId(currentPage, size, id, memberPointQuery);
    }
}
