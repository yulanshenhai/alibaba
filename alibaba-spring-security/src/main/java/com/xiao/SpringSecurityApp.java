package com.xiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiao
 */
@SpringBootApplication
@MapperScan("com.xiao.mapper")
public class SpringSecurityApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApp.class, args);
    }
}