package com.maple.rabbitmq.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive {
    public static void main(String[] args){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.96.151.94");
        factory.setPort(5672);
        factory.setUsername("xsf");
        factory.setPassword("sbxsf");

        Connection connection= null;
        Channel channel=null;

        try {
            connection=factory.newConnection();
            channel=connection.createChannel();
            channel.queueDeclare("myDirectQueue",true,false,false,null);
            channel.exchangeDeclare("directExchange","direct",true);
            channel.queueBind("myDirectQueue","directExchange","directRoutingKey");
            /**
             * 监听某个队列并获取队列中的数据
             * 注意：
             *   当前被讲定的队列必须已经存在并正确的绑定到了某个交换机中
             */
            channel.basicConsume("myDirectQueue",true,"",new DefaultConsumer(channel){
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message=new String(body);
                    System.out.println("消费者------>"+message);
                    System.out.println("消息接收成功");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
