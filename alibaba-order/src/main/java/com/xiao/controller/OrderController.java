package com.xiao.controller;

import com.xiao.param.OrderInsertParam;
import com.xiao.service.OrderService;
import com.xiao.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao
 * @since 2022-10-25
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    public Result insert(@RequestBody OrderInsertParam orderInsertParam) {
        log.info("请求：添加一条订单记录：{}", orderInsertParam);
        int result = orderService.insert(orderInsertParam);
        log.info("响应：{}", result > 0 ? "插入成功" : "插入失败");
        return result > 0 ? Result.ok() : Result.fail(-1, "插入失败");
    }
}
