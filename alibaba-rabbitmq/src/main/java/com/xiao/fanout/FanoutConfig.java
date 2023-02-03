package com.xiao.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiao
 */
@Configuration
public class FanoutConfig implements BeanPostProcessor {

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
     * 管理 [fanout广播模式] 下的 [队列A]
     */
    @Bean
    public Queue fanoutQueueA() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue(FanoutConstant.QUEUE_A, true, false, false);
    }

    /**
     * 管理 [fanout广播模式] 下的 [队列B]
     */
    @Bean
    public Queue fanoutQueueB() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue(FanoutConstant.QUEUE_B, true, false, false);
    }

    /**
     * 管理 [fanout广播模式] 下的 [交换机]
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        // args: 交换机名，持久化，无消费者时不删除交换机
        return new FanoutExchange(FanoutConstant.EXCHANGE, true, false);
    }

    /**
     * 将 [队列A] 绑定到指定 [交换机]
     */
    @Bean
    public Binding bindFanoutA() {
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    /**
     * 将 [队列B] 绑定到指定 [交换机]
     */
    @Bean
    public Binding bindFanoutB() {
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

    /**
     * Bean的后置处理器：在项目启动后执行，自动创建队列和交换机
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        rabbitAdmin.declareQueue(fanoutQueueA());
        rabbitAdmin.declareQueue(fanoutQueueB());
        rabbitAdmin.declareExchange(fanoutExchange());
        return null;
    }
}