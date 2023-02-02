package com.xiao.controller;

import com.xiao.RocketmqConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao
 */
@RestController
@RequestMapping("/api/v1/rocketmq/producer")
public class ProducerController {

    /**
     * RocketMQ推荐使用 `o.a.r.s.c.RocketMQTemplate` 作为Java客户端
     */
    @Autowired
    private RocketMQTemplate rocketmqTemplate;

    @GetMapping("/send-msg")
    public String sendMsg(@RequestParam String msg,
                          @RequestParam String topic,
                          @RequestParam String tag) {
        // 同步发送消息
        rocketmqTemplate.convertAndSend(topic + ":" + tag, msg);
        return "消息同步发送完毕";
    }
}
