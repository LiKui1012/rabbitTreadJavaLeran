package com.example.rabbit.demo.notice.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;



//程序的输入指的是把从文件读取到的内容存储到为程序分配的内存区域里面去。
// 流，什么是流，流无非就是两根管道，一根向里，一根向外，向里向外都是对于我们自己写的程序来说，流分为各种各样的类型，
// 不同的分类方式又可以分为不同的类型，根据方向来分，分为输入流和输出流，根据读取数据的单位的不同，
// 又可以分为字符流和字节流，除此之外，还可以分为节点流和处理流，节点流就是直接和数据源连接的流，
// 处理流就是包在其它流上面的流，处理流不是直接和数据源连接，而是从数据源读取到数据以后再通过处理流处理一遍。
// 转换流：交字节流转换为字符流操作
// 缓冲流也包含了四个类：BufferedInputStream、BufferedOutputStream、BufferedReader和BufferedWriter。
// 流都是成对的，没有流是是不成对的，肯定是一个in，一个out。
public class TestBufferStreamCopy {

    public static void main(String args[]) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\pmx\\Desktop\\copy.txt"));
            BufferedWriter copyOut = new BufferedWriter(new FileWriter("C:\\Users\\pmx\\Desktop\\copyOut.txt"));

            //在节点流FileWriter的外面再套一层处理流BufferedWriter
            String s = null;
            for (int i = 0; i < 100; i++) {
                s = String.valueOf(Math.random());//“Math.random()”将会生成一系列介于0～1之间的随机数。
                // static String valueOf(double d)这个valueOf()方法的作用就是把一个double类型的数转换成字符串
                //valueOf()是一个静态方法，所以可以使用“类型.静态方法名”的形式来调用
                bw.write(s);//把随机数字符串写入到指定文件中
                bw.newLine();//调用newLine()方法使得每写入一个随机数就换行显示
            }
            bw.flush();//调用flush()方法清空缓冲区
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pmx\\Desktop\\copy.txt"));
            //在节点流FileReader的外面再套一层处理流BufferedReader
            while ((s = br.readLine()) != null) {
                //使用BufferedReader处理流里面提供String readLine()方法读取文件中的数据时是一行一行读取的
                //循环结束的条件就是使用readLine()方法读取数据返回的字符串为空值后则表示已经读取到文件的末尾了。
                System.out.println(s+"+++++++++++++++");
                copyOut.write(s);
            }
            copyOut.flush();//调用flush()方法清空缓冲区
            copyOut.close();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
