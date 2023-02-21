package com.xiao.controller;

import com.xiao.mapper.ProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/** @author JoeZhou */
@RestController
@RequestMapping("/api/v1/consumer")
public class ConsumerController {

    /**
     * DubboReference注解: 注入远程服务接口，version用于指定服务的版本
     * loadbalance = "roundrobin": 负载均衡策略
     */
    @DubboReference(version = "1.0.0", loadbalance = "roundrobin")
    private ProviderService providerService;

    @GetMapping("/get-data")
    public Map<String, Object> getData() {
        String consumerData = "消费者的数据";
        String providerData = providerService.getData();
        Map<String, Object> result = new HashMap<>(2);
        result.put("consumerData", consumerData);
        result.put("providerData", providerData);
        return result;
    }
}