package com.xiao.topic;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author xiao
 * localhost:8001/rabbitmq/topic/send-msg?msg=hello&routingKey=xiao.a
 * localhost:8001/rabbitmq/topic/send-msg?msg=hello&routingKey=xiao.a.b
 */
@RestController
@RequestMapping("/api/v1/rabbitmq/topic")
public class TopicController {

    /**
     * RabbitMQ推荐使用 `o.s.a.r.c.RabbitTemplate` 作为Java客户端
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @SneakyThrows
    @GetMapping("/send-msg")
    public String sendMsg(String msg, String routingKey) {
        // 发送消息
        rabbitTemplate.convertAndSend(
                TopicConstant.EXCHANGE,
                routingKey,
                msg + ": " + UUID.randomUUID());
        return "消息发送成功";
    }
}
