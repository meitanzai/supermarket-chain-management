package com.cqupt.th.supermarket;

import com.cqupt.th.supermarket.entity.Region;
import com.cqupt.th.supermarket.service.RegionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class SupermarketChainManagementApplicationTests {
    @Autowired
    @Qualifier("regionService")
    private RegionService regionService;

    @Test
    void contextLoads() {
        //中国省份为数组
        String s = "城口县、丰都县、垫江县、忠县、云阳县、奉节县、巫山县、巫溪县、石柱土家族自治县、秀山土家族苗族自治县、酉阳土家族苗族自治县、彭水苗族土家族自治县";
        String[] strings = s.split("、");
        ArrayList<Region> regions = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            Region region = new Region();
            region.setName(strings[i]);
            region.setParentId(32);
            regions.add(region);
        }
        regionService.saveBatch(regions);
    }

}
