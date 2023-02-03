package com.xiao.consumer;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author xiao
 */
@Component
@RocketMQMessageListener(
        // 消费者组名
        consumerGroup = "test-consumer-group",
        // 监听的主题名
        topic = "test-topic",
        // 监听的标签名，默认为 `*` 表示监听全部标签
        selectorExpression = "test-tag",
        // 并发模式CONCURRENTLY: 同组消费者并发接收消息，默认值
        // 顺序模式ORDERLY: 同组消费者按顺序接收消息
        consumeMode = ConsumeMode.CONCURRENTLY,
        // 集群模式CLUSTERING: 同组消费者平均分摊消费消息，默认值
        // 广播模式BROADCASTING: 同组消费者没人消费一次消息
        messageModel = MessageModel.CLUSTERING
)
public class ConsumerServerB implements RocketMQListener<String> {

    /**
     * 在Broker投递消息时触发
     *
     * @param msg 消息
     */
    @Override
    public void onMessage(String msg) {
        System.out.println("ConsumerB接收到消息: " + msg);
    }
}
