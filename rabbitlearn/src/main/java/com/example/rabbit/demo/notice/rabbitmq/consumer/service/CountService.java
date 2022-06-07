package com.example.rabbit.demo.notice.rabbitmq.consumer.service;

import org.springframework.scheduling.annotation.Async;

public interface CountService {
    @Async("rabbitServiceExecutor")
    void counntAdd(String data);
}
