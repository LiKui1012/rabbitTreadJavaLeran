package com.example.rabbit.demo.notice.aop.model;

import org.springframework.util.StringUtils;

public class demoTest {
    public static void main(String[] args) {
        AccountDto accountDto = new AccountDto();
        if(accountDto==null){
            System.out.println(
                    234
            );
        }
        if((accountDto.getBalance()!=null && accountDto.getBalance()>0)){
            System.out.println(
                    1222
            );
        }
        System.out.println(
                3453
        );
    }
}
