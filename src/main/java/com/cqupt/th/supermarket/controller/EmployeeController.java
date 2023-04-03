package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.query.EmployeeQuery;
import com.cqupt.th.supermarket.service.EmployeeService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("getEmployeeListPageByPositionId/{currentPage}/{pageSize}/{positionId}")
    public CommonResult getEmployeeListPageByPositionId(@PathVariable("currentPage") Integer currentPage,
                                                        @PathVariable("pageSize") Integer pageSize,
                                                        @PathVariable("positionId") Integer positionId,
                                                        @RequestBody(required = false) EmployeeQuery employeeQuery) {
        return employeeService.getEmployeeListPageByPositionId(currentPage, pageSize, positionId, employeeQuery);
    }

    @GetMapping("getManagerList")
    public CommonResult getManagerList() {
        return employeeService.getManagerList();
    }

    @GetMapping("getWarehouseManagerList")
    public CommonResult getWarehouseManagerList() {
        return employeeService.getWarehouseManagerList();
    }

    @PostMapping("{currentPage}/{size}")
    public CommonResult getEmployeeListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @RequestBody(required = false) EmployeeQuery employeeQuery) {
        return employeeService.getEmployeeListPage(currentPage, size, employeeQuery);
    }
}
