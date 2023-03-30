package com.cqupt.th.supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 连锁超市管理应用程序
 *
 * @author TianHong
 * @date 2023/03/25
 */
@SpringBootApplication(scanBasePackages = "com.cqupt.th.supermarket")
public class SupermarketChainManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupermarketChainManagementApplication.class, args);
    }

}
