package com.maple.rabbitmq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
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
            channel.exchangeDeclare("topicExchange","topic",true);

            String message="topic的测试消息！";

            channel.basicPublish("topicExchange","aa",null,message.getBytes("utf-8"));
            System.out.println("消息发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
