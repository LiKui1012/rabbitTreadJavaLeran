package com.example.rabbit.demo.notice.threadpool.threadSafe.service;

import org.springframework.scheduling.annotation.Async;

public interface MoneyService {
    //保证全局标量money不变
    @Async("myTaskAsyncPool")
//    @Async
    void moneyAdd(int i);
}
