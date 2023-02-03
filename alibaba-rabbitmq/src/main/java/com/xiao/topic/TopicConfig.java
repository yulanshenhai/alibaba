package com.xiao.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiao
 */
@Configuration
public class TopicConfig implements BeanPostProcessor {

    /**
     * RabbitAdmin用来创建队列和交换机
     */
    @Autowired
    private RabbitAdmin rabbitAdmin;

    /**
     * 管理RabbitAdmin实例
     */
    @Bean
    @ConditionalOnMissingBean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        // 创建RabbitAdmin实例，并设置在项目启动时将该实例自动交给Spring管理
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /**
     * 管理 [topic主题模式] 下的 [队列A]
     */
    @Bean
    public Queue topicQueueA() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue(TopicConstant.QUEUE_A, true, false, false);
    }

    /**
     * 管理 [topic主题模式] 下的 [队列B]
     */
    @Bean
    public Queue topicQueueB() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue(TopicConstant.QUEUE_B, true, false, false);
    }

    /**
     * 管理 [topic主题模式] 下的 [交换机]
     */
    @Bean
    public TopicExchange topicExchange() {
        // args: 交换机名，持久化，无消费者时不删除交换机
        return new TopicExchange(TopicConstant.EXCHANGE, true, false);
    }

    /**
     * 将 [队列A] 绑定到指定 [交换机] 并指定 [路由键]
     */
    @Bean
    public Binding bindTopicA() {
        return BindingBuilder.bind(topicQueueA())
                .to(topicExchange())
                .with("xiao.*");
    }

    /**
     * 将 [队列B] 绑定到指定 [交换机] 并指定 [路由键]
     */
    @Bean
    public Binding bindTopicB() {
        return BindingBuilder.bind(topicQueueB())
                .to(topicExchange())
                .with("xiao.#");
    }

    /**
     * Bean的后置处理器：在项目启动后执行，自动创建队列和交换机
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        rabbitAdmin.declareQueue(topicQueueA());
        rabbitAdmin.declareQueue(topicQueueB());
        rabbitAdmin.declareExchange(topicExchange());
        return null;
    }
}