package com.example.rabbit.demo.notice.rabbitmq.producer;

import com.example.rabbit.demo.notice.rabbitmq.config.Constants;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Slf4j
@RestController
//也可以走定时任务发
public class ProMoreReqMoreConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //当交换机由消费者或者UI界面手动已经绑定过了，可以直接publish接口，如果没有绑定需要重新绑定
    @GetMapping(path = "publish2")
    //用第一个QUICK_GOOD_RABBIT，EXCHANGE_NAME=exchange_lk.topic
    public CorrelationData publish(String exchange, String routing) {
        String data="lazy.good.elephan";
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("投递id"+correlationData.getId());
        System.out.println("投递消息"+data);
        //correlationData参数是开启消息投递确认机制，投递到borker的机制
        //当消息发送到borker，但是borker没有找到正确的交换机或者找到交换机，但是没有找到队列
        //1)如果，不加correlationData，消息会被丢弃，生产者无感知
        //2)如果，只加correlationData，控制台返回Returned message but no callback available，不会处理异常逻辑
        //3)如果写了回调，会调用回调逻辑，控制台会返回自定义逻辑，不会返回Returned message
        //3.1)并且加了correlationData，confirm会返回另一个correlationData
        //3.2)如果不加correlationData，confirm的correlationData是null
//        rabbitTemplate.convertAndSend(exchange, routing, data);

        try{
            for (int i=0;i<50;i++) {
                rabbitTemplate.convertAndSend(exchange, routing, data, new CorrelationData(UUID.randomUUID().toString()));
            }
        }catch (Exception e){
            System.out.println("连接mq失败");
            //todo 存储到db中进行重发
        }
        return correlationData;
    }

//    @Scheduled(cron = "*/59 * * * * ?")
    @GetMapping(path = "cron")
    public void sendNotice() {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("当前时间" + format);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");
        Connection conn = null;
        try {
            conn = factory.newConnection();
            Channel channel = conn.createChannel();
            //发送到broker并由broker返回一个id,broker服务器实体
            //这一步确认是否发送到broker，其实是是否发到exchange的意思
            channel.confirmSelect();
            for (int i = 0; i < 1000; i++) {
                String message=String.valueOf(i);
                channel.basicPublish(Constants.EXCHANGE_NAME,
                        Constants.LAZY_BROWN_FOX, null,
                        message.getBytes());
            }
            channel.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
