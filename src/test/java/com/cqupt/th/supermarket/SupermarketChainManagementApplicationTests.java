package com.cqupt.th.supermarket;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.th.supermarket.entity.Brand;
import com.cqupt.th.supermarket.entity.Permission;
import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.mapper.BrandMapper;
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
@Resource
private BrandMapper brandMapper;

    @Test
    void contextLoads() {
////        Integer[] integers = new Integer[]{};
////        System.out.println(integers.length);
//        List<Permission> permission_value = permissionMapper.selectList(new QueryWrapper<Permission>().ne("permission_value", null));
//        System.out.println(permission_value);
        //分割可口可乐、百事可乐、雀巢、达利园、卡乐比、麦当劳、肯德基、必胜客、欧莱雅、兰蔻、雅诗兰黛、资生堂、SK-II、美宝莲、MAC、雅漾、NIKE、Adidas、PUMA、Levi's、GAP、ZARA、H&M、索尼、三星、LG、海尔、美的、格力、松下、飞利浦、宝马、奥迪、奔驰、大众、福特、丰田、本田、雪佛兰、苹果、华为、小米、三星、OPPO、VIVO、联想、戴尔、拜耳、辉瑞、罗氏、诺华、杨森制药、瑞士诺和诺华、默沙东、卡地亚、蒂芙尼、宝格丽、周大福、周生生、周六福、百威、青岛、哈尔滨、五粮液、茅台、洋河、剑南春、泸州老窖、李宁、安踏、特步、阿迪达斯、耐克、匹克、锐步、鬼冢虎
        String[] strings = {"可口可乐、百事可乐、雀巢、达利园、卡乐比、麦当劳、肯德基、必胜客、欧莱雅、兰蔻、雅诗兰黛、资生堂、SK-II、美宝莲、MAC、雅漾、NIKE、Adidas、PUMA、Levi's、GAP、ZARA、H&M、索尼、三星、LG、海尔、美的、格力、松下、飞利浦、宝马、奥迪、奔驰、大众、福特、丰田、本田、雪佛兰、苹果、华为、小米、三星、OPPO、VIVO、联想、戴尔、拜耳、辉瑞、罗氏、诺华、杨森制药、瑞士诺和诺华、默沙东、卡地亚、蒂芙尼、宝格丽、周大福、周生生、周六福、百威、青岛、哈尔滨、五粮液、茅台、洋河、剑南春、泸州老窖、李宁、安踏、特步、阿迪达斯、耐克、匹克、锐步、鬼冢虎"};
        for (String string : strings) {
            String[] split = string.split("、");
            for (String s : split) {
                Brand brand = new Brand();
                brand.setName(s);
                brandMapper.insert(brand);
            }
        }
    }


}
