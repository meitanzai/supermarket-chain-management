package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author th
 * @date 2023/3/31 16:17
 */
@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;
}
