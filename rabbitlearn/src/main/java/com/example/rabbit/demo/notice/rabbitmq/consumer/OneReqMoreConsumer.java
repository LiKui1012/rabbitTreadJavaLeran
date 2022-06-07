package com.example.rabbit.demo.notice.rabbitmq.consumer;


import com.example.rabbit.demo.notice.rabbitmq.config.Constants;
import com.example.rabbit.demo.notice.rabbitmq.config.Count;
import com.example.rabbit.demo.notice.rabbitmq.consumer.service.CountService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
//多次请求，一个绑定键绑定一个交换器，路由到一个队列，但是对应多个消费者消费

@Service
@Lazy(false)
//将mq消息丢进线程池
//消息消费确认
//1)一个队列对应多个消费者：即相同的路由键大量请求，由多个消费者实例（单个队列）分摊消费,先测试这个
//2)不同的路由键消息，大量请求，可以将某些请求归到一个队列中，由多个或者单个消费者实例消费；另外一些请求归到另一个队列中,由...
public class OneReqMoreConsumer {
    //durable消息队列是否持久化：false-服务挂掉后队列消失 true-不会消失；true和false仅仅对于队列而言，不保证队列里面的消息
    //autoDelete,是否删除队列：前提，消费者从来没有从队列中获取到消息，是不会删除队列。当消费者
    //获取到队列里面的消息，不管有没有获取完，此时所有消费者断开连接  fasle  不删除队列，true 删除队列

    @Autowired
    private CountService countService;
    /**
     * 当队列已经存在时，直接指定队列名的方式消费
     *
     * @param data
     */
    int i = 0;

    //封装消费消息确认 将消息用线程异步处理
    //测试concurrency多线程消费时间
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory" ,
            //在消费者里面：交换机通过路由键绑定队列，此时在mq的客户端已经绑定了关系，及时服务断了，关系还在
            //如果消费者，生产者在不同的服务，第一次启动的时候先启动消费者，后面无所谓-但是需要要保证消费的消息不会丢失，
            //最好先启动消费者
            bindings = @QueueBinding(
                    value = @Queue(value = Constants.QUEUE_1, durable = "true" , autoDelete = "false"),
                    exchange = @Exchange(value = Constants.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
                    key = Constants.BIND1_QUEUE_1), concurrency = "1")
    public void consumerQueue1(Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        synchronized (Count.lock) {
            Count.count++;
            if (Count.count > 99) {
                System.out.println("消费者1第" + Count.count + "次消费");
            }
        }
        String data = null;
        try {
//            Thread.sleep(1 * 50);
//            data = new String(message.getBody(), "UTF-8");

            data = new String(message.getBody(), "gb2312");
        } catch (Exception e) {
        }
        System.out.println("消费者1===消息唯一标识ID:" + deliveryTag + "===消息内容:" + data);
        try {
            /**
             * 确认消息，参数说明：
             * long deliveryTag：唯一标识 ID。
             * boolean multiple：是否批处理，当该参数为 true 时，
             * 则可以一次性确认 deliveryTag 小于等于传入值的所有消息。
             */
//            System.out.println("正常消费");
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            e.printStackTrace();
            /**
             * 拒绝消息，参数说明：
             * long deliveryTag：唯一标识 ID。
             * boolean requeue：如果 requeue 参数设置为 true，
             * 则 RabbitMQ 会重新将这条消息存入队列，以便发送给下一个订阅的消费者；
             * 如果 requeue 参数设置为 false，则 RabbitMQ 立即会还把消息从队列中移除，
             * 而不会把它发送给新的消费者。
             */
            System.out.println("发生异常，执行拒绝策略或者重新入列或者删除");
            try {
                channel.basicNack(deliveryTag, true, true);
            } catch (Exception e1) {
            }
//            channel.basicReject(deliveryTag, true);
        }
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory" ,
            bindings = @QueueBinding(
                    value = @Queue(value = Constants.QUEUE_1, durable = "true" , autoDelete = "false"),
                    exchange = @Exchange(value = Constants.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
                    key = Constants.BIND1_QUEUE_1), concurrency = "1")
    public void consumerQueue2(Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String data = null;
        try {
//            Thread.sleep(1 * 50);
            data = new String(message.getBody(), "gb2312");
        } catch (Exception e) {
        }
        System.out.println("消费者2===消息唯一标识ID:" + deliveryTag + "===消息内容:" + data);
        synchronized (Count.lock) {
            Count.count++;
            if (Count.count > 99) {
                System.out.println("消费者2第" + Count.count + "次消费");
            }
        }
        try {
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常，执行拒绝策略");
        }
    }


    @RabbitListener(containerFactory = "rabbitListenerContainerFactory" ,
            bindings = @QueueBinding(
                    value = @Queue(value = Constants.QUEUE_1, durable = "true" , autoDelete = "false"),
                    exchange = @Exchange(value = Constants.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
                    key = Constants.BIND2_QUEUE_2), concurrency = "1")
    public void consumerQueue3(Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String data = null;
        try {
//            Thread.sleep(1 * 50);
            data = new String(message.getBody(), "gb2312");
        } catch (Exception e) {
        }
        System.out.println("消费者3===消息唯一标识ID:" + deliveryTag + "===消息内容:" + data);
        synchronized (Count.lock) {
            Count.count++;
            if (Count.count > 99) {
                System.out.println("消费者3第" + Count.count + "次消费");
            }
        }
        try {
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常，执行拒绝策略");
        }
    }


    //定时任务 模拟正在消费，服务中断，查看消息是否持久化
    //消息异步线程处理
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory" ,
            bindings = @QueueBinding(
                    value = @Queue(value = Constants.QUEUE_2, durable = "true" , autoDelete = "false"),
                    exchange = @Exchange(value = Constants.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
                    key = Constants.BIND3_QUEUE_1), concurrency = "1")
    public void consumerQueue4(Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String data = null;
        try {
            data = new String(message.getBody(), "gb2312");
        } catch (Exception e) {
        }
        System.out.println("消费者4===消息唯一标识ID:" + deliveryTag + "===消息内容:" + data);
        countService.counntAdd(data);
        try {
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常，执行拒绝策略");
        }
    }


}
