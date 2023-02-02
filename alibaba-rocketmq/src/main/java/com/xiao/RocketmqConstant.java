package com.xiao;

/**
 * @author xiao
 */
public interface RocketmqConstant {

    /**
     * RocketMQ的NAMESRV的地址
     */
    String RMQ_NAMESRV_ADDR = "192.168.44.77:9876";

    /**
     * 测试Topic主题
     */
    String TOPIC_TEST = "topic-test";

    /**
     * 测试Tag标签
     */
    String TAG_TEST = "tag-test";

    /**
     * 测试消费者组名
     */
    String CONSUMER_GROUP_TEST = "consumer-group-test";

    /**
     * 测试生产者组名
     */
    String PRODUCER_GROUP_TEST = "producer-group-test";

    /**
     * 测试异步消息的Topic主题
     */
    String SYNC_TOPIC_TEST = "topic-test:sync";

    /**
     * 测试同步消息的Topic主题
     */
    String ASYNC_TOPIC_TEST = "topic-test:async";

    /**
     * 测试单向消息的Topic主题
     */
    String ONE_WAY_TOPIC_TEST = "topic-test:one-way";

    /**
     * 测试有序消息的Topic主题
     */
    String ONE_WAY_ORDERLY_TOPIC_TEST = "topic-test:one-way-orderly";
}
