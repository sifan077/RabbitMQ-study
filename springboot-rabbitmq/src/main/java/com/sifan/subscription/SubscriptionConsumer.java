package com.sifan.subscription;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConsumer {

    @RabbitListener(queues = SubscriptionConfig.QUEUE_1)
    @RabbitHandler
    public void processA(String message) {
        System.out.println("customerA消费queueA消息成功: " + message);
    }

    @RabbitListener(queues = SubscriptionConfig.QUEUE_2)
    @RabbitHandler
    public void processB1(String message) {
        System.out.println("customerB1消费queueB消息成功: " + message);
    }

    @RabbitListener(queues = SubscriptionConfig.QUEUE_2)
    @RabbitHandler
    public void processB2(String message) {
        System.out.println("customerB2消费queueB消息成功: " + message);
    }
}