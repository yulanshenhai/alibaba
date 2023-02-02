package com.xiao;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xiao
 */
@Slf4j
@RestController
@RequestMapping("/rocketmq/msg-type")
public class RocketmqController {

    @Autowired
    private RocketMQTemplate rocketmqTemplate;

    @RequestMapping("/sync")
    public String syncSend() {
        // 测试RMQ消息的同步发送效果
        SendResult sendResult = rocketmqTemplate.syncSend(RocketmqConstant.SYNC_TOPIC_TEST, "一条同步消息", 6000L);
        log.info("同步发送完毕，返回结果：{}", sendResult.getSendStatus());
        return "发送完毕";
    }

    @RequestMapping("/async")
    @SneakyThrows
    public String asyncSend() {
        /* 测试RMQ消息的异步发送效果
         * 异步发送的时侯，可能会出现junit比返回值先结束的情况
         * 建议在junit测试末尾使主线程睡眠一段时间，等待返回值结果的输出 */
        rocketmqTemplate.asyncSend(RocketmqConstant.ASYNC_TOPIC_TEST, "一条异步消息",
                new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("异步发送成功，返回结果：{}", sendResult.getSendStatus());
                    }

                    @Override
                    public void onException(Throwable e) {
                        log.error("异步发送失败", e);
                    }
                }
        );
        log.info("如果我先输出，证明是异步发送");
        TimeUnit.SECONDS.sleep(5L);
        return "发送完毕";
    }

    @RequestMapping("/one-way")
    public String oneWay() {
        // 测试RMQ消息的单项发送效果
        rocketmqTemplate.sendOneWay(RocketmqConstant.ONE_WAY_TOPIC_TEST, "一条单向消息");
        log.info("单项发送完毕");
        return "发送完毕";
    }

    @RequestMapping("/one-way-orderly")
    public String oneWayOrderly() {
        // 测试RMQ消息的单项顺序发送效果
        for (int i = 0; i < 10; i++) {
            // 根据最后一个字符串参数进行hash()取余
            rocketmqTemplate.sendOneWayOrderly(RocketmqConstant.ONE_WAY_ORDERLY_TOPIC_TEST,
                    "单项顺序消息测试", "xx");
        }
        log.info("单项顺序发送完毕");
        return "发送完毕";
    }

}
