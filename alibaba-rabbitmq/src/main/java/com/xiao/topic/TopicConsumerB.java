package com.xiao.topic;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiao
 * <p> @RabbitListener: 设置监听哪条队列，队列自动创建
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(TopicConstant.QUEUE_B))
public class TopicConsumerB {

    @RabbitHandler
    public void consume(String msg) {
        System.out.println("队列B收到消息：" + msg);
    }
}