package com.sifan.route;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RouteProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;
    public void sendMessage() {
        System.out.println("发送消息 : error info warning"  );
        rabbitTemplate.convertAndSend(RouteConfig.DIRECT_EXCHANGE,"error","我是error");
        rabbitTemplate.convertAndSend(RouteConfig.DIRECT_EXCHANGE,"info","我是info");
        rabbitTemplate.convertAndSend(RouteConfig.DIRECT_EXCHANGE,"warning","我是warning");

    }

}
