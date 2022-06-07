package com.example.rabbit.demo.notice.rabbitmq.consumer.serviceImp;

import com.example.rabbit.demo.notice.rabbitmq.config.Count;
import com.example.rabbit.demo.notice.rabbitmq.consumer.service.CountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CountServiceImp implements CountService {
    public void counntAdd(String data) {
        synchronized (Count.lock) {
            Count.count++;
        }
        log.info("===第" +  Count.count + "次执行前，data===" + Thread.currentThread().getName());
    }
}

