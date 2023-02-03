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
public class HeadersConsumerA {

    @RabbitListener(queuesToDeclare = @Queue(HeadersConstant.QUEUE_A))
    @SneakyThrows
    public void consume(Message message) {
        // 获取消息中的消息配置
        MessageProperties messageProperties = message.getMessageProperties();
        // 获取内容类型
        String contentType = messageProperties.getContentType();
        // 消费消息
        System.out.println("队列A收到消息：" + new String(message.getBody(), contentType));
    }

}