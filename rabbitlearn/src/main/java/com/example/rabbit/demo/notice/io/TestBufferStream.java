package com.example.rabbit.demo.notice.io;

//处理流 是包含在节点流（字节输入流 字节输出流等）上面的

import lombok.Data;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Data
public class TestBufferStream {
    public static void main(String args[]) {
                 FileInputStream fis = null;
                 try {
                         fis = new FileInputStream("C:\\Users\\pmx\\Desktop\\问题.txt");
                         // 在FileInputStream节点流的外面套接一层处理流BufferedInputStream
                         BufferedInputStream bis = new BufferedInputStream(fis);
                         int c = 0;
                         System.out.println((char) bis.read());
                         bis.mark(100);// 在第100个字符处做一个标记
//                         for (int i = 0; i <= 10 && (c = bis.read()) != -1; i++) {
//                                 System.out.print((char) c);
//                             }
                         bis.reset();// 重新回到原来标记的地方
//                         for (int i = 0; i <= 10 && (c = bis.read()) != -1; i++) {
//                                 System.out.print((char) c);
//                             }
                         bis.close();
                     } catch (FileNotFoundException e) {
                         e.printStackTrace();
                     } catch (Exception e1) {
                         e1.printStackTrace();
                     }
             }
}
