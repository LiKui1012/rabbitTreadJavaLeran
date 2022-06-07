package com.example.rabbit.demo.notice.reflection;

import com.example.rabbit.demo.notice.aop.model.AccountDto;

import java.lang.reflect.Constructor;

public class test {
    public static void main(String[] args) {
        //反射1根据对象的getClass方法
        AccountDto accountDto = new AccountDto();
        Class<? extends AccountDto> aClass = accountDto.getClass();
        String name = aClass.getName();
        Constructor<?>[] constructors = aClass.getConstructors();
        System.out.println(
                name.toString() + constructors.toString()
        );
        //反射2根据对象.Class静态方法
        Class stuClass2 = AccountDto.class;
        System.out.println("是否为同一个class对象?" + (aClass == stuClass2));
        //反射3根据对象 forName()方法获取
        try {
        Class stuClass3 = Class.forName("com.example.rabbit.demo.notice.aop.model.AccountDto");
        //System.out.println(stuClass3); 输出仍然是class fanshe.Student
        System.out.println("是否为同一个class对象?" + (stuClass3 == stuClass2));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
