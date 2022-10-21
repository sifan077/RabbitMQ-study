package com.sifan.route;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sifan.MQConnection;

/**
 * 生产商
 * Routing路由模式 分布式日志
 * @author 思凡
 * @date 2022/10/21
 */
public class Producer {

    //交换机名称
    static final String DIRECT_EXCHANGE = "direct_exchange";
    //队列名称
    static final String DIRECT_QUEUE_1 = "direct_queue_1";
    //队列名称
    static final String DIRECT_QUEUE_2 = "direct_queue_2";

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = MQConnection.getConnectionFactory();
        //2创建长连接
        Connection connection = connectionFactory.newConnection();
        //3创建channel
        Channel channel = connection.createChannel();
        //声明队列
        //String queue,  队列名
        // boolean durable, 持久化
        // boolean exclusive, 排他的
        // boolean autoDelete, 自动删除
        // Map<String, Object> arguments 属性
        channel.queueDeclare(DIRECT_QUEUE_1, true, false, false, null);
        channel.queueDeclare(DIRECT_QUEUE_2, true, false, false, null);

        // 声明交换机
        // String exchange,  交换机名称
        // BuiltinExchangeType type, 交换机类型
        // boolean durable,  持久化
        // boolean autoDelete, 自动删除
        // Map<String, Object> arguments 属性
        channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT, true, false, null);

        //队列绑定交换机
        // String queue, 队列名称
        // String exchange, 交换机名称
        // String routingKey 路由键
        channel.queueBind(DIRECT_QUEUE_1, DIRECT_EXCHANGE, "error");
        channel.queueBind(DIRECT_QUEUE_2, DIRECT_EXCHANGE, "info");
        channel.queueBind(DIRECT_QUEUE_2, DIRECT_EXCHANGE, "error");
        channel.queueBind(DIRECT_QUEUE_2, DIRECT_EXCHANGE, "warning");

        //4发消息
        // String exchange,  交换机
        // String routingKey, 路由键
        // AMQP.BasicProperties props, 属性
        // byte[] body 消息      string byte[] char[]如何相互转换的？
        String msg = "hello rabbitmq!routing error";
        channel.basicPublish(DIRECT_EXCHANGE, "error", null, msg.getBytes());

        String msg1 = "hello rabbitmq!routing info";
        channel.basicPublish(DIRECT_EXCHANGE, "info", null, msg1.getBytes());

        String msg2 = "hello rabbitmq!routing warning";
        channel.basicPublish(DIRECT_EXCHANGE, "warning", null, msg2.getBytes());

        //5关闭连接  资源关闭的顺序，先关后出来的资源，最后关，第一个资源
        channel.close();
        connection.close();
    }
}