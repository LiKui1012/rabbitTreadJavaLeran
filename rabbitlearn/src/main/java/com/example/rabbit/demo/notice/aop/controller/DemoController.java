package com.example.rabbit.demo.notice.aop.controller;

import com.example.rabbit.demo.notice.aop.model.AccountDto;
import com.example.rabbit.demo.notice.aop.aopUtils.ParamCheck;
import com.example.rabbit.demo.notice.aop.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

    @PostMapping(value = "/testAopDemo")
    public AccountDto testAopDemo(@RequestBody AccountDto dto) {
        log.info("post-controller层 accountName： " + dto.getAccountName());
        log.info("post-controller层 accountNo： " + dto.getAccountNo());
        return demoService.getUserAccount();
    }

    //传值

    @GetMapping(value = "/testAopException")
    public AccountDto testAopException(@RequestParam(value = "accountName", required = false) String accountName,
                                       @RequestParam(value = "accountNo", required = false) String accountNo) {
        log.info("get-controller层 accountName： " + accountName);
        log.info("get-controller层 accountNo： " + accountNo);
        return demoService.getUserAccount();
    }




    /**
     *测试@RequestParam注解
     * @param name
     * @return
     */
    @GetMapping("/hello1")
    public void hello1(@RequestParam String name) {
        System.out.println("测试@RequestParam注解"+name);
    }


    @GetMapping("/hello4")
    public void hello4(@RequestParam String name) {
        System.out.println("测试@RequestParam注解"+name);
        AccountDto accountDto = new AccountDto();
        accountDto.getAccountName().substring(1);



    }

    /**
     * 测试@ParamCheck注解
     * @param name
     * @return
     */
    @GetMapping("/hello2")
    public void hello2(@ParamCheck String name) {
        System.out.println("测试@ParamCheck注解"+name);
    }

    /**
     * 测试@ParamCheck与@RequestParam一起时
     * @param name
     * @return
     */
    @GetMapping("/hello3")
    public void hello3(@ParamCheck @RequestParam String name) {
        System.out.println("测试@ParamCheck与@RequestParam一起时"+name);
    }

}
