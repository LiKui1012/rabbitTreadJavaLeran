package com.example.rabbit.demo.notice.designmodel;

public class signleLazy {
    public static void main(String[] args) {
        System.out.println(
                "111"
        );
        getInstance();
    }
    //为了不让其他类直接访问该成员	懒汉式单例，在使用时创建对象
    //1、私有静态变量
    private static signleLazy person=null;
    //2、将构造器私有化
    private signleLazy(){
        System.out.println("构造方法");

    }
    //3、提供一个静态方法，并返回该类的对象
    public static signleLazy getInstance(){
        if(person==null){
            System.out.println(
                    "第一次静态"
            );
            //第一次访问
            person=new signleLazy();;
        }
        return person;
    }
    public void sayHello(){
        System.out.println("sayHello方法");
    }
}
