package com.maple.rabbitmq;

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
            connection = factory.newConnection();
            channel=connection.createChannel();

            channel.queueDeclare("myQueue",true,false,false,null);

            /**
             * 接收消息
             * 参数 1 为当前消费者需要监听的队列名 ，队列名必须要与发送时的队列名完全一致否则接收不到消息
             * 参数 2 为消息是否自动确认，true表示自动确认接收完消息以后会自动将消息从队列中移除
             * 参数 3 为消息接收者的标签，用于当多个消费者同时监听一个队列时用于确认不通消费者，通常为空字符串即可
             * 参数 4 为消息接收的回调方法这个方法中具体完成对消息的处理代码
             * 注意：使用了basicConsume方法以后，会启动一个线程在持续的监听队列，如果队列中有信息的数据进入则会自动接收消息
             *      因此不能关闭连接和通道对象
             */
            channel.basicConsume("myQueue",true,"",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body,"utf-8");
                    System.out.println("消费者--->"+message);
                    System.out.println("消息接收成功");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
