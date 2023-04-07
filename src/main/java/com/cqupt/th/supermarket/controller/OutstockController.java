package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.service.OutstockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author th
 * @date 2023/4/7 12:49
 */
@RestController
@CrossOrigin
@RequestMapping("/outstock")
public class OutstockController {
    @Autowired
    @Qualifier("outstockService")
    private OutstockService outstockService;
}
