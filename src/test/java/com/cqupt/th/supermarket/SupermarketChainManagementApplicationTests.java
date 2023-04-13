package com.cqupt.th.supermarket;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.th.supermarket.entity.Permission;
import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.mapper.PermissionMapper;
import com.cqupt.th.supermarket.service.RegionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SupermarketChainManagementApplicationTests {
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;
    @Resource
    private PermissionMapper permissionMapper;

    @Test
    void contextLoads() {
//        Integer[] integers = new Integer[]{};
//        System.out.println(integers.length);
        List<Permission> permission_value = permissionMapper.selectList(new QueryWrapper<Permission>().ne("permission_value", null));
        System.out.println(permission_value);
    }


}
