package com.sifan.base;

import com.sifan.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class MsgProducer{
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTING_KEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(BaseConfig.EXCHANGE_A, BaseConfig.ROUTING_KEY_A, content, correlationId);
    }
}