package com.xiao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao
 * <p> `@RefreshScope`: 启用动态刷新Nacos配置文件内容的功能
 */
@RefreshScope
@RestController
@RequestMapping("/api/v1/nacos-config")
public class NacosConfigController {

    @Value("${project.author}")
    private String author;

    @Value("${project.env}")
    private String env;

    @GetMapping("/get-author")
    public String getAuthor() {
        return author;
    }

    @GetMapping("/get-env")
    public String getEnv() {
        return env;
    }
}