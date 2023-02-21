package com.xiao.service.impl;

import com.xiao.mapper.ProviderService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author xiao
 * <p>DubboService注解: 用于对外暴露服务，version用于指定服务的版本
 */
@DubboService(version = "1.0.0")
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String getData() {
        // 模拟提供者提供的一些数据
        return "提供者的一堆数据";
    }
}