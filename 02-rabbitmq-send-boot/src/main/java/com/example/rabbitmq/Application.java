package com.example.rabbitmq;

import com.example.rabbitmq.service.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        /*SpringApplication.run(Application.class, args);*/
        ApplicationContext ac=SpringApplication.run(Application.class, args);
        SendService service= (SendService) ac.getBean("sendService");

        service.sendMessage("Boot的Direct测试数据");

        service.sendFanoutMessage("Boot的Fanout测试数据");
        service.sendTopicMessage("Boot的Topic测数据数据key 为 aa.bb.cc");
    }

}
