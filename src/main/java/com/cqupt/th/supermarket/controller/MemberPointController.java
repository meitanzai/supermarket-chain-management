package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.service.MemberPointService;
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
@RequestMapping("/memberPoint")
public class MemberPointController {
    @Autowired
    @Qualifier("memberPointService")
    private MemberPointService memberPointService;
}
