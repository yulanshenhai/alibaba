package com.xiao.controller;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xiao
 */
@RestController
@RequestMapping("/api/v1/rocketmq/producer")
public class ProducerController {

    /**
     * RocketMQ推荐使用 `o.a.r.s.c.RocketMQTemplate` 作为Java客户端
     */
    @Autowired
    private RocketMQTemplate rocketmqTemplate;

    /** 测试向Broker发送消息 */
    @GetMapping("/convert-and-send")
    public String convertAndSend(String msg, String topic, String tag) {
        rocketmqTemplate.convertAndSend(topic + ":" + tag, msg);
        return "消息发送完毕";
    }

    /**
     * 测试: RocketMQ同步-随机发送消息
     */
    @GetMapping("/sync-send")
    public String syncSend(String msg, String topic, String tag) {
        SendResult sendResult = rocketmqTemplate.syncSend(topic + ":" + tag, msg);
        System.out.println("同步消息结果:" + sendResult.getSendStatus());
        return "同步-随机消息发送完毕";
    }

    /**
     * 测试: RocketMQ异步-随机发送消息
     */
    @SneakyThrows
    @GetMapping("/async-send")
    public String asyncSend(String msg, String topic, String tag) {
        rocketmqTemplate.asyncSend(topic + ":" + tag, msg, new SendCallback() {

            /** 当消息发送成功时触发 */
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步-随机消息发送成功，返回结果: " + sendResult.getSendStatus());
            }

            /** 当消息发送异常时触发 */
            @Override
            public void onException(Throwable e) {
                System.out.println("异步-随机消息发送失败: " + e);
            }

        });
        System.out.println("如果我先输出，证明是异步发送");

        // 主线程睡眠5秒钟，防止控制方法提前结束，来不及打印异步方法的返回值
        TimeUnit.SECONDS.sleep(5L);
        return "异步-随机消息发送完毕";
    }

    /**
     * 测试: RocketMQ单向-随机发送消息
     */
    @GetMapping("/send-one-way")
    public String sendOneWay(String msg, String topic, String tag) {
        rocketmqTemplate.sendOneWay(topic + ":" + tag, msg);
        return "单向-随机发送消息完毕";
    }

    /**
     * 测试: RocketMQ同步-顺序发送消息
     */
    @GetMapping("/sync-send-orderly")
    public String syncSendOrderly(String msg, String topic, String tag) {
        // 根据最后一个字符串参数进行hash()取余，该参数的值随意
        for (int i = 0; i < 10; i++) {
            rocketmqTemplate.syncSendOrderly(topic + ":" + tag, (msg + i), "xx");
        }
        return "同步-顺序发送消息完毕";
    }

    /**
     * 测试: RocketMQ异步-顺序发送消息
     */
    @SneakyThrows
    @GetMapping("/async-send-orderly")
    public String asyncSendOrderly(String msg, String topic, String tag) {
        // 异步-顺序发送消息: 根据最后一个字符串参数进行hash()取余，该参数的值随意
        for (int i = 0; i < 10; i++) {
            rocketmqTemplate.asyncSendOrderly(topic + ":" + tag, (msg + i), "xx", new SendCallback() {
                /** 当消息发送成功时触发 */
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("异步发送成功，返回结果: " + sendResult.getSendStatus());
                }

                /** 当消息发送异常时触发 */
                @Override
                public void onException(Throwable e) {
                    System.out.println("异步发送失败: " + e);
                }
            });
        }

        System.out.println("如果我先输出，证明是异步发送");

        // 睡眠5秒钟，防止控制方法提前结束，来不及打印异步方法的返回值
        TimeUnit.SECONDS.sleep(5L);
        return "异步-顺序发送消息完毕";
    }

    /**
     * 测试: RocketMQ单向-顺序发送消息
     */
    @GetMapping("/send-one-way-orderly")
    public String sendOneWayOrderly(String msg, String topic, String tag) {
        // 单向-顺序发送消息: 根据最后一个字符串参数进行hash()取余，该参数的值随意
        for (int i = 0; i < 10; i++) {
            rocketmqTemplate.sendOneWayOrderly(topic + ":" + tag, (msg + i), "xx");
        }
        return "单向-顺序发送消息完毕";
    }
}
