package com.xiao.fanout;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiao
 * <p> @RabbitListener: 设置监听哪条队列，队列自动创建
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(FanoutConstant.QUEUE_A))
public class FanoutConsumerA {
    @RabbitHandler
    public void consume(Map<String, Object> msg) {
        System.out.println("队列A收到消息: " + msg);
    }
}