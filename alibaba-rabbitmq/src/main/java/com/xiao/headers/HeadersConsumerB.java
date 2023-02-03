package com.xiao.headers;

import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiao
 * <p> @RabbitListener: 设置监听哪条队列，队列自动创建
 */
@Component
public class HeadersConsumerB {

    @RabbitListener(queuesToDeclare = @Queue(HeadersConstant.QUEUE_B))
    @SneakyThrows
    public void consume(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        String contentType = messageProperties.getContentType();
        System.out.println("队列B收到消息：" + new String(message.getBody(), contentType));
    }

}