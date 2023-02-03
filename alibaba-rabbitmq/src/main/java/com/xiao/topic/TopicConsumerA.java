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
@RabbitListener(queuesToDeclare = @Queue(TopicConstant.QUEUE_A))
public class TopicConsumerA {

    @RabbitHandler
    public void consume(String msg) {
        System.out.println("队列A收到消息：" + msg);
    }
}