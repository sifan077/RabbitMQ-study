package com.sifan.route;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由订阅配置
 *
 * @author 思凡
 * @date 2022/10/21
 */
@Configuration
public class RouteConfig {
    //交换机名称
    static final String DIRECT_EXCHANGE = "direct_exchange";
    //队列名称
    static final String DIRECT_QUEUE_1 = "direct_queue_1";
    //队列名称
    static final String DIRECT_QUEUE_2 = "direct_queue_2";

    @Bean("routeQueue1")
    public Queue routeQueueA() {
        return new Queue(DIRECT_QUEUE_1);
    }

    @Bean("routeQueue2")
    public Queue routeQueueB() {
        return new Queue(DIRECT_QUEUE_2);
    }

    @Bean
    public DirectExchange routeExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    Binding bindExchangeError(Queue routeQueue1, DirectExchange routeExchange){
        return BindingBuilder.bind(routeQueue1).to(routeExchange).with("error");
    }

    @Bean
    Binding bindExchangeError2(Queue routeQueue2, DirectExchange routeExchange){
        return BindingBuilder.bind(routeQueue2).to(routeExchange).with("error");
    }

    @Bean
    Binding bindExchangeInfo(Queue routeQueue2,DirectExchange routeExchange){
        return BindingBuilder.bind(routeQueue2).to(routeExchange).with("info");
    }

    @Bean
    Binding bindExchangeWarn(Queue routeQueue2,DirectExchange routeExchange){
        return BindingBuilder.bind(routeQueue2).to(routeExchange).with("warning");
    }

}
