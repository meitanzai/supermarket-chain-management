package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
