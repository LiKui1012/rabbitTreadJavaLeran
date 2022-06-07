package com.example.rabbit.demo.notice.threadpool.threadSafe.createThread;

import com.example.rabbit.demo.notice.threadpool.config.Money;

public class MyThread extends Thread {
    public void run() {//重写run()方法的实现
        System.out.println("MyThread Money.money" + Money.money);
        synchronized (Money.lock) {
            for (int i = 0; i < 500000; i++) {
                Money.money++;
            }
        }
        System.out.println(
                Thread.currentThread().getName()+"+++++++++++MyThread应该是800020+++++++++" + Money.money
        );

    }
}