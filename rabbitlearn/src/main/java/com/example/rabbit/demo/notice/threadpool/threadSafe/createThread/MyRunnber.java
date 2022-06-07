package com.example.rabbit.demo.notice.threadpool.threadSafe.createThread;

import com.example.rabbit.demo.notice.threadpool.config.Money;

public class MyRunnber implements Runnable {
    public void run() {//重写run()方法的实现
        System.out.println(
                "MyRunnber Money.money" + Money.money
        );
        synchronized (Money.lock) {
            for (int i = 0; i < 300000; i++) {
                Money.money++;
            }
        }
        System.out.println(
                Thread.currentThread().getName()+"+++++++++++MyRunnber 应该是300020+++++++++" + Money.money
        );
    }
}