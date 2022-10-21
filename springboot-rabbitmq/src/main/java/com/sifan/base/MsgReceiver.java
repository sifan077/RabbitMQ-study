package com.sifan.base;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MsgReceiver {
    @RabbitListener(queues = BaseConfig.QUEUE_A)
    @RabbitHandler
    public void process(String content) {
        System.out.println("接收处理队列A当中的消息： " + content);
    }
}