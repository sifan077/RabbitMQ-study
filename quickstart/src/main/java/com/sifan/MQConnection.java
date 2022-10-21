package com.sifan;

import com.rabbitmq.client.ConnectionFactory;

public class MQConnection {
    public static ConnectionFactory getConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("106.15.250.225");
        //连接端口;默认为 5672
        connectionFactory.setPort(5672);
        //虚拟主机名称;默认为 /
        connectionFactory.setVirtualHost("/");
        //连接用户名；默认为guest
        connectionFactory.setUsername("root");
        //连接密码；默认为guest
        connectionFactory.setPassword("123456");
        return connectionFactory;
    }
}
