package com.example.rabbit.demo.notice.rabbitmq.config.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 2 * @Author: Yanweiwei
 * 3 * @Date: 2021/7/6 10:44
 * 4
 */
@Slf4j
@Configuration
@EnableAsync
public class InjectExecutorConfig {

    @Value("${task.pool.corePoolSize}")
    private int corePoolSize;

    @Value("${task.pool.maxPoolSize}")
    private int maxPoolSize;

    @Value("${task.pool.keepAliveSeconds}")
    private int queueCapacity;

    @Value("${task.pool.queueCapacity}")
    private String namePrefix;


    @Bean(name = "rabbitServiceExecutor")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new VisualThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(namePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


}
