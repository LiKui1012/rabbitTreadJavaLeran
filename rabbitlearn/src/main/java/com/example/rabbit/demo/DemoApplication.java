package com.example.rabbit.demo;

import com.example.rabbit.demo.notice.threadpool.config.TaskThreadPoolConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableCaching // 启用缓存功能
@EnableScheduling // 开启定时任务功能
@EnableTransactionManagement
@EnableAsync
@EnableConfigurationProperties({TaskThreadPoolConfig.class} )
@MapperScan(basePackages="com.example.rabbit.demo.notice.mybaties.*")
public class DemoApplication {
    @Value("${student.name}")
    private  String name;
    private final StringRedisTemplate stringRedisTemplate;
    @Autowired
    public DemoApplication(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    //设置手动消费消息
    @Bean(value = "rabbitListenerContainerFactory")
    @ConditionalOnClass
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(CachingConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //用
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
