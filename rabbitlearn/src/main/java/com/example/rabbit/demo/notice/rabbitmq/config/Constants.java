package com.example.rabbit.demo.notice.rabbitmq.config;

//交换机topic 模式
//路由键中的第一个单词描述了速度，第二个描述了品格，第三个描述了物种：<speed>.<colour>.<species>
//在消费者里面绑定关系，因为启动服务先启动的消费者：交换机通过路由键绑定队列
public class Constants {
    //两个队列接收除了LAZY_GOOD_MALE_RABBIT所有路由键的消息
    public   static  final String QUEUE_1 = "queue.lk1";
    public   static  final String QUEUE_2 = "queue.lk2";

    //需要创建三个消费者，绑定键分别为下面三个
    public   static  final String BIND1_QUEUE_1 = "*.good.*";
    public   static  final String BIND2_QUEUE_2 = "*.*.rabbit";
    public   static  final String BIND3_QUEUE_1 = "lazy.#";

    //路由键
    public  static  final String QUICK_GOOD_RABBIT = "quick.good.rabbit";
    public  static  final String LAZY_GOOD_ELEPHANT= "lazy.good.elephan";
    public  static  final String QUICK_GOOD_FOX= "quick.good.dog";
    public  static  final String LAZY_BROWN_FOX= "lazy.bad.dog";

    public  static  final String QUICK_GOOD_MALE_RABBIT= "quick.good.male.rabbit";
    //异常路由键
    public  static  final String LAZY_GOOD_MALE_RABBIT= "lazy.good.male.rabbit";



    public   static  final String EXCHANGE_NAME = "exchange_lk.topic";
    //ROUTING_KEY路由建 一般是生产者用到，交换机通过路由键和绑定键做匹配，然后路由到对应队列，至于怎么路由，取决于交换机的类型，和绑定键的格式
    //BINDKEY绑定建 一般是消费者在启动时，自动监听创建，通过它绑定交换机和队列
//    public  static  final String ROUTING_KEY = "routingKey_lk.topic";



}
