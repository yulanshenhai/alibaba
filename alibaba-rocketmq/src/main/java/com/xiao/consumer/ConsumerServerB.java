package com.xiao.consumer;

import com.xiao.RocketmqConstant;
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
        consumerGroup = RocketmqConstant.CONSUMER_GROUP_TEST,
        // 监听的主题名
        topic = RocketmqConstant.TOPIC_TEST,
        // 监听的标签名，默认为 `*` 表示监听全部标签
        selectorExpression = RocketmqConstant.TAG_TEST,
        // 同组的消费者并发接收消息，默认值，改为ConsumeMode.ORDERLY表示按顺序接收消息
        consumeMode = ConsumeMode.CONCURRENTLY,
        // 同组的消费者平均分摊消费该消息，默认值，改为MessageModel.BROADCASTING表示全部消费该消息
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
