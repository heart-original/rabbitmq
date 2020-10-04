package com.example.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class RabbitmqConfig {
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("bootDirectExchange");
    }

    @Bean
    public Queue directQueue(){
        return new Queue("bootDirectQueue");
    }

    @Bean
    public Binding directBinding(Queue directQueue, DirectExchange directExchange){
        //完成绑定
        // 参数 1 为需要绑定的队列
        // 参数 2 为需要绑定的交换机
        // 参数 3绑定时的RoutingKey
        return BindingBuilder.bind(directQueue).to(directExchange).with("bootDirectRoutingKey");
    }
}
