package com.example.rabbit.demo.notice.rabbitmq.config;

public class Count {
    public static Object lock = new Object();
    public static volatile  int count = 0;
}
