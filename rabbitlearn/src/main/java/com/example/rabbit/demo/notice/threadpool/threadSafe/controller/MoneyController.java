package com.example.rabbit.demo.notice.threadpool.threadSafe.controller;

import com.example.rabbit.demo.notice.threadpool.config.Money;
import com.example.rabbit.demo.notice.threadpool.threadSafe.service.MoneyService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

//多线程对全局变量修改，保证变量正确
@Slf4j
@RestController
public class MoneyController {
//    @Autowired
//    private AsyncTask asyncTask;

    @Autowired
    private MoneyService moneyService;
    @GetMapping(path = "testPool")
    public void AsyncTaskTest(@RequestParam(required = false,value = "assetId")String assetId) throws InterruptedException, ExecutionException {
        if(StringUtil.isNullOrEmpty(assetId)){
            for (int i = 0; i < 1000000; i++) {
//            asyncTask.doTask1(new StringBuffer(i));
                moneyService.moneyAdd(i);
            }
        }else{
            moneyService.moneyAdd(-1);
            log.info("==========模拟压测过程中看看用户的请求有没有被处理" + assetId);
        }
        Thread.sleep(5 * 1000);
        log.info("All tasks finished.G" + Money.money);
    }
}
