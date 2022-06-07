package com.example.rabbit.demo.notice;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

public class basetest {

    /**
     * 对字符串md5加密(小写+字母)
     *
     * @param   Url传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public static void main(String[] args) {
        int s=3;
        while (s<2){
            System.out.println(
                    "+++"
            );
        }
        s=1;
//        while (s<2){
//            System.out.println(
//                    "+++11"
//            );
//        }

        Date date = new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 2);
        String expireTime = String.valueOf(date.getTime() / 1000);
        expireTime="1642732276";

        System.out.println(
                "date++++++" + date + "++++++++++res+++" + expireTime
        );
        String key = "zxvf";
        String path = "/20211212/mobile138xxxxxxx/121200_1000.mp4";
        String Url = expireTime + path + key;
        System.out.println(
                "Url=================" + Url
        );
        try {
            // 生成一个MD5加密计算摘要下面的MD5也可换成SHA或SHA-1  SHA也是一种和MD5类似的单向散列加密算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(Url.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5 = new BigInteger(1, md.digest()).toString(16);
            System.out.println(
                    md5 + "++++md5===================="+ new BigInteger(1, md.digest())
            );

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(
                    "异常" + e
            );
        }
    }

//        int num = 10;
//        foo(num);
//        System.out.println(num);
//        String str = "this is str";
//        tryChangeStr(str);
//        System.out.println(str);
//        User user = new User();
//        user.name = "zhangsan";
//        tryChangeObj(user);
//        System.out.println(user.name);
//
//
//        StringBuilder sb = new StringBuilder("iphone");
//        foo(sb);
//        System.out.println(sb.toString());
//        foo1(sb);
//        System.out.println(sb.toString());
//
//    }
//
//    static void foo1(StringBuilder builder) {
//        builder = new StringBuilder("ipad");
//        System.out.println(
//                "builder+++"+builder
//        );
//    }
//    static void foo(StringBuilder builder) {
//        builder.append("4");
//    }
//    static void foo(int value) {
//        value = 100;
//    }
//
//    static class User {
//        public String name;
//    }
//
//    public static void tryChangeStr(String formStr) {
//        formStr = "hi, this is formStr";
//    }
//
//    public static void tryChangeObj(User formUser) {
//        formUser.name = "lisi";
//    }


//        for(int i=0;i<10;i++){
//            System.out.println(
//                    "i+++++++++++++"+i
//            );
//        }
//        int a=0;
//        a++;
//        System.out.println(
//               a
//        );
//        int b=0;
//        ++b;
//        System.out.println(
//             b
//        );

//        String a = "A";
//        String d = new String("A");
//        String b = "A";
//        String c = new String("A");
//
//        System.out.println(
//                a.equals(d)
//        );
//        System.out.println(
//                a == d
//        );
//
//
//        System.out.println(
//                a.equals(b)
//        );
//        System.out.println(
//                a == b
//        );
//        System.out.println(
//                c.equals(d)
//        );
//        System.out.println(
//                c == d
//        );
//        System.out.println(
//                "++++++++++++++++++"
//        );
//        TestBufferStream testBufferStream1 = new TestBufferStream();
//        TestBufferStream testBufferStream2 = new TestBufferStream();
//        System.out.println(
//                testBufferStream1.equals(testBufferStream2)
//        );//因为重写了equals 要不然object的equals比较的还是对象
//
//        System.out.println(
//                testBufferStream1 == testBufferStream2
//        );
//        Integer i = 10;
//        int j = 10;
//        Integer integer = new Integer(10);
//        System.out.println(
//                "++++++++++++++++++"
//        );
//        System.out.println(
//                i == j
//        );
//        System.out.println(
//                i.equals(j)
//        );
//        System.out.println(
//                i == integer
//        );
//        System.out.println(
//                i.equals(integer)
//        );


    }
