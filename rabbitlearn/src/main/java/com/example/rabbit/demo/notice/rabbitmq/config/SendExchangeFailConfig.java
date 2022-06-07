package com.example.rabbit.demo.notice.rabbitmq.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


//producer—>rabbitmq broker—>exchange—>queue—>consumer
//消息从 producer 到 exchange 则会返回一个 confirmCallback 。
//消息从 exchange–>queue 投递失败则会返回一个 returnCallback 。

@Component
public class SendExchangeFailConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        //消息从 producer 到 exchange 则会返回一个 confirmCallback
        rabbitTemplate.setConfirmCallback(this);
        //todo  这是什么意思是不是等于correlationData
//        rabbitTemplate.setMandatory(true);
        //消息从 exchange–>queue 投递失败则会返回一个 returnCallback
        rabbitTemplate.setReturnsCallback(this);
    }
    //消息发送到broker
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息唯一标识：" + correlationData.getId()+"+++++ack++++"+ack);
        if(ack){
//            System.out.println("消息发送到exchange成功");
        }else{
            System.out.println("消息发送到exchange失败"+cause);
        }
    }
    //broker确认返回，找不到交换机或者队列
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        System.err.println("broker确认投递错误: " + returned);
    }

}
