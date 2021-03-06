package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("bootDirectExchange");
    }

    @Bean
    public Queue directQueue(){
        return new Queue("bootDirectQueue");
    }

    /**
     *  配置一个队列和交换机的绑定
     * @param directQueue  需要绑定的队列的对象，参数名必须要与某个@Bean的方法名完全相同这样就会自动进行注入
     * @param directExchange  需要绑定的交换机的对象，参数名必须要与某个@Bean的方法名完全相同这样就会自动进行注入
     * @return
     */
    @Bean
    public Binding directBinding(Queue directQueue,DirectExchange directExchange){
        // 参数 1 为需要绑定的队列
        // 参数 2 为需要绑定的交换机
        // 参数 3绑定时的RoutingKey
        return BindingBuilder.bind(directQueue).to(directExchange).with("bootDirectRoutingKey");
    }

    //配置一个 Fanout类型的交换
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    //配置一个 Topic 类型的交换
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }
}
