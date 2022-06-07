package com.example.rabbit.demo.notice.aop.model;

import lombok.Data;

@Data
public class AccountDto {
    private String accountNo;
    private String accountName;
    private Double balance;
    private Integer status;
    private Boolean  flag;

}
