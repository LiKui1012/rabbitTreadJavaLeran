package com.example.rabbit.demo.notice.threadpool.threadSafe.createThread;

import com.example.rabbit.demo.notice.threadpool.config.Money;
import com.example.rabbit.demo.notice.threadpool.threadSafe.createThread.MyRunnber;
import com.example.rabbit.demo.notice.threadpool.threadSafe.createThread.MyThread;

//对全局变量修改保持 线程安全
//线程的start方法是异步的，当调用start方法后，主程序的代码会继续执行，同时start子线程方法也在执行，如果
//想让程序等start方法走完在继续走，那么需要加join，此时子线程是异步的但是主逻辑是同步的。。
//这里只是演示join的作用，代码实际意义不大
public class ThreadTest extends Thread {
    public static void main(String args[]) {
        System.out.println(
                "Money.money为" + Money.money
        );
        while (Money.money != 20) {
            Money.money++;
        }
        System.out.println(
                "i为" + Money.money
        );
        MyRunnber myR = new MyRunnber();
        Thread myRunnber1 = new Thread(myR, "runnber1");//要启动一个新的线程就必须new一个Thread对象出来
        Thread myRunnber2 = new Thread(myR, "runnber2");//要启动一个新的线程就必须new一个Thread对象出来
        myRunnber1.start();
        try {
            myRunnber1.join();
        } catch (InterruptedException e) {

        }
        myRunnber2.start();
        try {
            myRunnber2.join();
        } catch (InterruptedException e) {

        }
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread1.setName("myThread1");
        myThread2.setName("myThread2");
        myThread1.start();//调用start()方法启动新开辟的线程
        try {
            myThread1.join();//调用start()方法启动新开辟的线程
        } catch (InterruptedException e) {
        }

        myThread2.start();//调用start()方法启动新开辟的线程
        System.out.println(
                "最终i为" + Money.money
        );
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
        }
        System.out.println(
                "睡眠后最终i为" + Money.money
        );
    }
}


