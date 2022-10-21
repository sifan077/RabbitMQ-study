package com.sifan.subscription;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 高级配置
 * 配置 订阅模式
 *
 * @author 思凡
 * @date 2022/10/21
 */
@Configuration
public class SubscriptionConfig {
    public static final String QUEUE_1 = "fanout_queue_1";
    public static final String QUEUE_2 = "fanout_queue_2";
    public static final String EXCHANGE = "fanout_exchange";

    @Bean
    public Queue felixQueueA() {
        return new Queue(QUEUE_1);
    }

    @Bean
    public Queue felixQueueB() {
        return new Queue(QUEUE_2);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingExchangeA(Queue felixQueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(felixQueueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeB(Queue felixQueueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(felixQueueB).to(fanoutExchange);
    }

}
