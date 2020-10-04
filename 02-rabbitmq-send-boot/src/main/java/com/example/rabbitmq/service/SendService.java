package com.example.rabbitmq.service;

public interface SendService {
    void sendMessage(String message);
    void sendFanoutMessage(String message);
    void sendTopicMessage(String message);
}
