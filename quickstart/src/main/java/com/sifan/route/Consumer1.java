package com.sifan.route;

import com.rabbitmq.client.*;
import com.sifan.MQConnection;

import java.io.IOException;

/**
 * consumer1
 * 路由消费者1
 * @author 思凡
 * @date 2022/10/21
 */
public class Consumer1 {
    //交换机名称
    static final String DIRECT_EXCHAGE = "direct_exchange";
    //队列名称
    static final String DIRECT_QUEUE_1 = "direct_queue_1";
    //队列名称
    static final String DIRECT_QUEUE_2 = "direct_queue_2";

    public static void main(String[] args) throws Exception {
        //1创建连接工厂
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
        channel.queueDeclare(DIRECT_QUEUE_1,true,false,false,null);
        channel.queueDeclare(DIRECT_QUEUE_2,true,false,false,null);

        // 声明交换机
        // String exchange,  交换机名称
        // BuiltinExchangeType type, 交换机类型
        // boolean durable,  持久化
        // boolean autoDelete, 自动删除
        // Map<String, Object> arguments 属性
        channel.exchangeDeclare(DIRECT_EXCHAGE, BuiltinExchangeType.DIRECT,true,false,null);

        //队列绑定交换机
        // String queue, 队列名称
        // String exchange, 交换机名称
        // String routingKey 路由键
        channel.queueBind(DIRECT_QUEUE_1,DIRECT_EXCHAGE,"error");
        channel.queueBind(DIRECT_QUEUE_2,DIRECT_EXCHAGE,"info");
        channel.queueBind(DIRECT_QUEUE_2,DIRECT_EXCHAGE,"error");
        channel.queueBind(DIRECT_QUEUE_2,DIRECT_EXCHAGE,"warning");


        //4监听某个队列
        // String queue, 监听的队列名
        // boolean autoAck, 是否自动应答
        // Consumer callback 回调函数，收到消息，我要干啥
        com.rabbitmq.client.Consumer consumer=new DefaultConsumer(channel){
            // 回调函数，收到消息，我要干啥
            //  String consumerTag, 消费者标签
            // Envelope envelope, 信封 保存很多信息
            // AMQP.BasicProperties properties, 属性
            // byte[] body  消息字节数组
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //业务逻辑

                //现在的业务逻辑就是打印
//                System.out.println("consumerTag:"+consumerTag);
//                System.out.println("Exchange:"+envelope.getExchange());
//                System.out.println("RoutingKey:"+envelope.getRoutingKey());
//                System.out.println("DeliveryTag:"+envelope.getDeliveryTag()); //消息id

                System.out.println(new String(body));
            }
        };
        channel.basicConsume(DIRECT_QUEUE_1,true,consumer);

        //5 千万别关闭连接，要不然queue有了消息 推不过来了
//        channel.close();
//        connection.close();

    }
}