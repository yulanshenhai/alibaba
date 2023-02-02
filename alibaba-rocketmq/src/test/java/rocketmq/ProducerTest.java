package rocketmq;

import com.xiao.RocketmqConstant;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author xiao
 */
public class ProducerTest {

    /**
     * 模拟RMQ中的一个生产者，生产消息到Broker
     */
    @Test
    @SneakyThrows
    public void testProducer() {

        // 创建一个生产者实例并纳入指定的生产者组，组名自动创建
        DefaultMQProducer producer = new DefaultMQProducer(RocketmqConstant.PRODUCER_GROUP_TEST);

        // 指定NameSrv地址，集群逗号分隔
        producer.setNamesrvAddr(RocketmqConstant.RMQ_NAMESRV_ADDR);

        // 启动生产者
        producer.start();

        // 创建10个消息实例并发送到Broker，主题和标签都可以自动创建
        for (int i = 0; i < 10; i++) {

            // 创建消息实例，指定主题和标签
            Message message = new Message(
                    RocketmqConstant.TOPIC_TEST,
                    RocketmqConstant.TAG_TEST,
                    ("你好" + i).getBytes(StandardCharsets.UTF_8));

            // 将消息同步发送到Broker中，10秒超时，返回SendResult实例
            SendResult sendResult = producer.send(message, 10000);

            // 查看发送消息的结果
            System.out.printf("%d号消息的ID为%s，状态为%s\n",
                    i,
                    sendResult.getMsgId(),
                    sendResult.getSendStatus());
        }
    }
}
