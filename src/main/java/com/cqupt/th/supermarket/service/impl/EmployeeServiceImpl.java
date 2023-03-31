package com.cqupt.th.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.th.supermarket.entity.Employee;
import com.cqupt.th.supermarket.service.EmployeeService;
import com.cqupt.th.supermarket.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author 16075
* @description 针对表【employee(员工表)】的数据库操作Service实现
* @createDate 2023-03-31 16:17:01
*/
@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




