package com.example.rabbit.demo.notice.threadpool.threadSafe.createThread.deadLock;

//三线程顺序打印ABC两次  nofityall的唤醒的顺序的是栈，最新（时间最大）的wait的先被唤醒
public class Abc {

    public static Boolean isThreadA = true;
    public static Boolean isThreadB = false;
    public static Boolean isThreadC = false;

    public static void main(String[] args) {
        final Abc abc = new Abc();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 2; i++) {
                    int j=i+1;
                    synchronized (abc) {
                        while(!isThreadA) {
                            try {
                                System.out.println("等待前，isThreadA加锁"+isThreadA);
                                abc.wait();
                                try{
                                    Thread.sleep(4 * 1000);
                                    System.out.println(
                                            "让C先唤醒B"
                                    );
                                }catch (Exception e){

                                }
                                System.out.println("等待后，isThreadA释放锁"+isThreadA);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        System.err.println("A");
                        isThreadA = false;
                        isThreadB = true;
                        isThreadC = false;
//                        abc.notifyAll(); //第一次没有wait 所以无用
//                        System.out.println("A第"+j+"次唤醒");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 2; i++) {
                    int j=i+1;
                    synchronized (abc) {
                        while(!isThreadB) {
                            try {
                                System.out.println("等待前，isThreadB加锁"+isThreadB);
                                abc.wait();
                                System.out.println("等待后，isThreadB释放锁"+isThreadB);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        System.err.println("B");
                        isThreadA = false;
                        isThreadB = false;
                        isThreadC = true;
                        abc.notifyAll();
                        System.out.println("B第"+j+"次唤醒");
                    }
                }
            }
        }).start();
        try{
            Thread.sleep(2 * 1000);
            System.out.println(
                    "暂停C看日志打印"
            );
        }catch (Exception e){

        }

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 2; i++) {
                    int j=i+1;
                    synchronized (abc) {
                        while(!isThreadC) {
                            try {
                                System.out.println("等待前，isThreadC加锁"+isThreadC);
                                abc.wait();
                                System.out.println("等待后，isThreadC释放锁"+isThreadC);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        System.err.println("C");
                        isThreadA = true;
                        isThreadB = false;
                        isThreadC = false;
                        if(i==0){
                            abc.notifyAll();
                            System.out.println("C第"+j+"次唤醒");
                        }
                    }
                }
            }
        }).start();
    }


}
