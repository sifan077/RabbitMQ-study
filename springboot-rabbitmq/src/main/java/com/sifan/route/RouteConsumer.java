package com.sifan.route;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RouteConsumer {
    @RabbitListener(queues = RouteConfig.DIRECT_QUEUE_1)
    @RabbitHandler
    public void processA(String message) {
        System.out.println("消息路由到了队列1: " + message);
    }

    @RabbitListener(queues = RouteConfig.DIRECT_QUEUE_2)
    @RabbitHandler
    public void processB1(String message) {
        System.out.println("消息路由到了队列2: " + message);
    }

}
