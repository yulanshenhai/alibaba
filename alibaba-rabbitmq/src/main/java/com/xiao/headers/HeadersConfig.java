package com.xiao.headers;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiao
 */
@Configuration
public class HeadersConfig implements BeanPostProcessor {

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
     * 管理 [headers请求头模式] 下的 [队列A]
     */
    @Bean
    public Queue headersQueueA() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue(HeadersConstant.QUEUE_A, true, false, false);
    }

    /**
     * 管理 [headers请求头模式] 下的 [队列B]
     */
    @Bean
    public Queue headersQueueB() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue(HeadersConstant.QUEUE_B, true, false, false);
    }

    /**
     * 管理 [headers请求头模式] 下的 [交换机]
     */
    @Bean
    public HeadersExchange headersExchange() {
        // args: 交换机名，持久化，无消费者时不删除交换机
        return new HeadersExchange(HeadersConstant.EXCHANGE, true, false);
    }

    /***
     * 将 [队列A] 绑定到指定 [交换机]
     */
    @Bean
    public Binding bindHeadersA() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("key_one", "java");
        map.put("key_two", "rabbit");
        //全匹配
        return BindingBuilder.bind(headersQueueA())
                .to(headersExchange())
                .whereAll(map).match();
    }

    /***
     * 将 [队列B] 绑定到指定 [交换机]
     */
    @Bean
    public Binding bindHeadersB() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("headers_A", "coke");
        map.put("headers_B", "sky");
        //部分匹配
        return BindingBuilder.bind(headersQueueB())
                .to(headersExchange())
                .whereAny(map).match();
    }

    /**
     * Bean的后置处理器：在项目启动后执行，自动创建队列和交换机
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        rabbitAdmin.declareQueue(headersQueueA());
        rabbitAdmin.declareQueue(headersQueueB());
        rabbitAdmin.declareExchange(headersExchange());
        return null;
    }
}