package com.xiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiao
 * <p>
 * <p>EnableDiscoveryClient：启动Nacos功能
 * <p>MapperScan：扫描Mapper类
 * <p>SpringBootApplication：启动注解
 */
@EnableDiscoveryClient
@MapperScan("com.xiao.mapper")
@SpringBootApplication
public class ProductApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class, args);
    }
}
