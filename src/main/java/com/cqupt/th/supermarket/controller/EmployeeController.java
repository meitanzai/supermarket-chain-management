package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Employee;
import com.cqupt.th.supermarket.query.EmployeeQuery;
import com.cqupt.th.supermarket.service.EmployeeService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/3/31 16:17
 */
@RestController
@PreAuthorize("hasAuthority('employee:employee:index')")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @PostMapping("employeeListPageByPositionId/{currentPage}/{pageSize}/{positionId}")
    public CommonResult getEmployeeListPageByPositionId(@PathVariable("currentPage") Integer currentPage,
                                                        @PathVariable("pageSize") Integer pageSize,
                                                        @PathVariable("positionId") Integer positionId,
                                                        @RequestBody(required = false) EmployeeQuery employeeQuery) {
        return employeeService.getEmployeeListPageByPositionId(currentPage, pageSize, positionId, employeeQuery);
    }

    @PostMapping("employeeListPageByStoreId/{currentPage}/{pageSize}/{storeId}")
    public CommonResult getEmployeeListPageByStoreId(@PathVariable("currentPage") Integer currentPage,
                                                     @PathVariable("pageSize") Integer pageSize,
                                                     @PathVariable("storeId") Integer storeId,
                                                     @RequestBody(required = false) EmployeeQuery employeeQuery) {
        return employeeService.getEmployeeListPageByStoreId(currentPage, pageSize, storeId, employeeQuery);
    }

    @PostMapping("employeeListPageByWarehouseId/{currentPage}/{pageSize}/{warehouseId}")
    public CommonResult getEmployeeListPageByWarehouseId(@PathVariable("currentPage") Integer currentPage,
                                                         @PathVariable("pageSize") Integer pageSize,
                                                         @PathVariable("warehouseId") Integer warehouseId,
                                                         @RequestBody(required = false) EmployeeQuery employeeQuery) {
        return employeeService.getEmployeeListPageByWarehouseId(currentPage, pageSize, warehouseId, employeeQuery);
    }

    @GetMapping("storeManagerList")
    public CommonResult getStoreManagerList() {
        return employeeService.getStoreManagerList();
    }

    @GetMapping("warehouseManagerList")
    public CommonResult getWarehouseManagerList() {
        return employeeService.getWarehouseManagerList();
    }

    @PostMapping("{currentPage}/{size}")
    public CommonResult getEmployeeListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @RequestBody(required = false) EmployeeQuery employeeQuery) {
        return employeeService.getEmployeeListPage(currentPage, size, employeeQuery);
    }

    @DeleteMapping("/batch/{ids}")
    public CommonResult deleteBatchEmployee(@PathVariable("ids") Integer[] ids) {
        return employeeService.deleteBatchEmployee(ids);
    }

    @DeleteMapping("{id}")
    public CommonResult deleteEmployeeById(@PathVariable("id") Integer id) {
        return employeeService.deleteEmployeeById(id);
    }

    @PutMapping("{id}")
    public CommonResult updateEmployeeById(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(id, employee);
    }

    @PostMapping
    public CommonResult addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PostMapping("isExistWorkNumber")
    public CommonResult isExistWorkNumber(@RequestBody Employee employee) {
        return employeeService.isExistWorkNumber(employee);
    }
}
