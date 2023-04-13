package com.cqupt.th.supermarket.service;

import com.cqupt.th.supermarket.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.th.supermarket.query.EmployeeQuery;
import com.cqupt.th.supermarket.utils.CommonResult;

/**
 * @author 16075
 * @description 针对表【employee(员工表)】的数据库操作Service
 * @createDate 2023-03-31 16:17:01
 */
public interface EmployeeService extends IService<Employee> {

    CommonResult getEmployeeListPageByPositionId(Integer currentPage, Integer pageSize, Integer positionId, EmployeeQuery employeeQuery);

    CommonResult getManagerList();

    CommonResult getWarehouseManagerList();

    CommonResult getEmployeeListPage(Integer currentPage, Integer size, EmployeeQuery employeeQuery);

    CommonResult deleteBatchEmployee(Integer[] ids);

    CommonResult deleteEmployeeById(Integer id);

    CommonResult updateEmployeeById(Integer id, Employee employee);

    CommonResult addEmployee(Employee employee);

    CommonResult getEmployeeListPageByStoreId(Integer currentPage, Integer pageSize, Integer storeId, EmployeeQuery employeeQuery);

    CommonResult getEmployeeListPageByWarehouseId(Integer currentPage, Integer pageSize, Integer warehouseId, EmployeeQuery employeeQuery);

    CommonResult getWorkNumberByWorkNumber(Employee employee);
}
