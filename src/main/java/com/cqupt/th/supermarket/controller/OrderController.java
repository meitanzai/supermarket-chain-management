package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author th
 * @date 2023/4/5 21:59
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;
}
