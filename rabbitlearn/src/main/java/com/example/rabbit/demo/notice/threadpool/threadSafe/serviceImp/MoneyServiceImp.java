package com.example.rabbit.demo.notice.threadpool.threadSafe.serviceImp;

import com.example.rabbit.demo.notice.threadpool.config.Money;
import com.example.rabbit.demo.notice.threadpool.threadSafe.service.MoneyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MoneyServiceImp implements MoneyService {
//    @Async("myTaskAsyncPool")
    //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    //默认的线程池不安全，比如压测的时候，当请求大于核心线程数，则把请求放入无线大的队列当中，这样会造成后面的请求一直没有处理
    //设置可能导致内存溢出，所以需要自定义线程池名字
    public void moneyAdd(int  i){
        if(i==-1){
            //模拟压测过程中，用户的请求
            log.info("++++++++++++用户请求++++++++");
        }else{
            log.info("===第"+i+"次执行前，money为==="+Money.money);
            //加锁保证线程安全
            synchronized (Money.lock) {
                Money.money++;
            }
        }

    }

}
