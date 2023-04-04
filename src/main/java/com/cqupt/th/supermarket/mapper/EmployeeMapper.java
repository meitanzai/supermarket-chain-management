package com.cqupt.th.supermarket.mapper;

import com.cqupt.th.supermarket.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 16075
* @description 针对表【employee(员工表)】的数据库操作Mapper
* @createDate 2023-03-31 16:17:01
* @Entity com.cqupt.th.supermarket.entity.Employee
*/
public interface EmployeeMapper extends BaseMapper<Employee> {

    void updateEmployeeByStoreId(@Param("storeId") Integer storeId);

    void updateEmployeeByStoreIds(@Param("storeIds") Integer[] storeIds);

    void updateEmployeeByWarehouseId(@Param("warehouseId") Integer warehouseId);
}




