package com.example.rabbit.demo.notice.designmodel;

public class signleHunger {
    public static void main(String[] args) {
        System.out.println("饿汉模式");
        getInstance();
    }

    //1、 饿汉式单例模式，  在类加载时创建一个对象
    private static signleHunger student = new signleHunger();

    // 2 构造器私有化
    private signleHunger(){

    }
    // 3 提供返回类对象的静态方法
    public static signleHunger getInstance(){
        if(student !=null){
            System.out.println(
                    "不为空"
            );
            return student;
        }
        System.out.println("为空");
        return null;
    }


}
