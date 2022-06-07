package com.example.rabbit.demo.notice.aop.service;

import com.example.rabbit.demo.notice.aop.model.AccountDto;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    public AccountDto getUserAccount() {
        AccountDto dto = new AccountDto();
        dto.setAccountNo("22133232001");
        dto.setAccountName("XXX");
        dto.setBalance(3000D);
        return dto;
    }
}
