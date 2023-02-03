package com.xiao.direct;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xiao
 */
@RestController
@RequestMapping("/api/v1/rabbitmq/direct")
public class DirectController {

    /**
     * RabbitMQ推荐使用 `o.s.a.r.c.RabbitTemplate` 作为Java客户端
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @SneakyThrows
    @GetMapping("/send-msg")
    public String sendMsg(String msg) {

        // 准备消息数据
        Map<String, Object> messageMap = new HashMap<>(2);
        messageMap.put("id", UUID.randomUUID().toString());
        messageMap.put("msg", msg);

        // 发送消息
        rabbitTemplate.convertAndSend(
                DirectConstant.EXCHANGE,
                DirectConstant.ROUTING_KEY,
                messageMap);

        return "消息发送成功";
    }
}
