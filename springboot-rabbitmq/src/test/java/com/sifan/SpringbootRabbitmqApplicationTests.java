package com.sifan;

import com.sifan.base.MsgProducer;
import com.sifan.route.RouteProducer;
import com.sifan.subscription.SubscriptionPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Autowired
    private MsgProducer msgBaseProducer;

    @Autowired
    private SubscriptionPublisher subscriptionPublisher;

    @Autowired
    private RouteProducer routeProducer;

    @Test
    void contextLoads() {
    }


    @Test
    // 测试最基础的消息队列
    public void testBase() {
        msgBaseProducer.sendMsg("hello,this is my msg");
    }

    @Test
    // 测试订阅模式
    public void testSubscription() {
        subscriptionPublisher.sendMessage();
    }

    @Test
    // 测试路由 分布式日志
    public void testRoute() {
        routeProducer.sendMessage();
    }

}
