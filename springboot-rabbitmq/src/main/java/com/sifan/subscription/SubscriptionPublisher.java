package com.sifan.subscription;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionPublisher {
 
    @Autowired
    private AmqpTemplate rabbitTemplate;
 
    /**
     * @Title 生产者将消息发送到交换机
     * @Description 生产者将消息发送到交换机
     * @Date 2020/2/29 13:29
     * @Param [i]
     * @return void
     **/
    public void sendMessage() {
        String message = "发布订阅模式-message";
        System.out.println("发送消息 : " + message);
        rabbitTemplate.convertAndSend(SubscriptionConfig.EXCHANGE,"",message);
    }
}