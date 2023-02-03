package com.xiao.fanout;

/**
 * @author xiao
 */
public interface FanoutConstant {

    /**
     * 广播模式：队列A
     */
    String QUEUE_A = "xiao.fanout.queue-a";
    /**
     * 广播模式：队列B
     */
    String QUEUE_B = "xiao.fanout.queue-b";
    /**
     * 广播模式：交换机
     */
    String EXCHANGE = "xiao.fanout.exchange";
}
