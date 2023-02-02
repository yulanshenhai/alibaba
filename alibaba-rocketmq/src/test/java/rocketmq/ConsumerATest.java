package rocketmq;

import com.xiao.RocketmqConstant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 模拟RMQ中的一个消费者，消费Broker中的消息
 *
 * @author xiao
 */
public class ConsumerATest {

    /**
     * 为了防止主线程退出，使用main替代Junit方法
     */
    @SneakyThrows
    public static void main(String[] args) {

        // 创建一个消费者实例并纳入指定的消费者组，组名自动创建
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                RocketmqConstant.CONSUMER_GROUP_TEST);

        // 指定NameSrv地址，集群逗号分隔
        consumer.setNamesrvAddr(RocketmqConstant.RMQ_NAMESRV_ADDR);

        // 消费者订阅指定的主题和标签：支持 `||` 分隔和 `*` 全部
        consumer.subscribe(RocketmqConstant.TOPIC_TEST, "*");

        // 消费者设置监听
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            try {
                msgs.forEach(messageExt ->
                        System.out.println("ConsumerA消费成功: " + new String(messageExt.getBody())));

                // 返回消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                System.out.println("ConsumerA消费失败: " + e);

                // 稍后重新尝试
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });

        // 启动消费者
        consumer.start();
        System.out.println("ConsumerA启动完毕，等待消费..");
    }

}
