package com.example.rabbit.demo.notice.javabase.equalsObject;

import com.example.rabbit.demo.notice.aop.model.AccountDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class sortTest {
    public static void main(String[] args) {
        AccountDto a = new AccountDto();
        AccountDto b = new AccountDto();
        AccountDto c = new AccountDto();
        AccountDto d = new AccountDto();
        AccountDto e = new AccountDto();
        AccountDto f = new AccountDto();
        AccountDto g = new AccountDto();
        AccountDto h = new AccountDto();
        AccountDto i = new AccountDto();
        AccountDto j = new AccountDto();
        AccountDto k = new AccountDto();

        a.setStatus(0);
        b.setStatus(1);
        c.setStatus(1);
        d.setStatus(0);
        e.setStatus(0);
        f.setStatus(1);
        g.setStatus(1);
        h.setStatus(1);
        i.setStatus(0);
        j.setStatus(1);
        k.setStatus(0);
        ArrayList<AccountDto> accountDtos = new ArrayList<>();
        accountDtos.add(a);
//        accountDtos.add(b);
//        accountDtos.add(c);
//        accountDtos.add(d);
//        accountDtos.add(e);
//        accountDtos.add(f);
//        accountDtos.add(g);
//        accountDtos.add(h);
//        accountDtos.add(i);
//        accountDtos.add(j);
//        accountDtos.add(k);
        for (int m=0;m<accountDtos.size()-1;m++){
//            System.out.println(
//                    accountDtos.get(m)
//            );
            if(accountDtos.get(m).getStatus().equals(accountDtos.get(m+1).getStatus())){
                accountDtos.get(m+1).setFlag(true);
            }else{
                accountDtos.get(m+1).setFlag(false);
            }
            System.out.println(
                    accountDtos.get(m)
            );
        }
        log.info("++++++++++++++++++++");
        System.out.println("++++++++++++"+
                accountDtos.get(accountDtos.size()-1)
        );
    }




}

