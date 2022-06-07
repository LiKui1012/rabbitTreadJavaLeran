package com.example.rabbit.demo.notice.javabase;

import java.util.ArrayList;
import java.util.List;

public class sort {
    static int sum = 0;

    public static void main(String[] args) {
        int arr[] = {8, 1, -1, 6, -5};

        int brr[] = {8, 1, -1, 6, -5};

        bobble(arr);
//        select(brr);
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < 4; i++) {
//            list.add(String.valueOf(i));
//        }
//        System.out.println(addMethod(list));
    }


    /***
     * 冒泡 sorting
     * @param arr
     */
    static void bobble(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println("i值+++++++++"+  i);
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            for (int k : arr) {
//                System.out.print(k+ "  ");
//            }
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }


    //todo 一个集合里面的数据按照数据数据大小倒序排列


    /****
     * 选择 sorting
     * @param arr
     */
    static void select(int arr[]) {
        System.out.println(
                "++++++++++++"
        );


        int k;
        for (int i = 0; i < arr.length - 1; ++i) {
            k = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[k] > arr[j]) {
                    k = j;
                }
            }
            if (i != k) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
            if (i == 0) {
                for (int m : arr) {
                    System.out.print(m + "  ");
                }
            }

        }
    }


    /****
     * 选择 递归
     * @param
     */

    public static int addMethod(List list) {
        if (list.size() > 0) {
            if (list.size() > 1) {
                int i = addMethod(list.subList(0, list.size() / 2));
                int j = addMethod(list.subList(list.size() / 2, list.size()));
                sum=i+j;
                System.out.println(
                        list.size()+"+++++i++++++++"+i+"++++++++=j+++++++++++"+j+"+++++sum+++"+sum
                );


            } else {
                sum = Integer.parseInt(list.get(0).toString());
                System.out.println(
                        sum+"size=0"
                );
            }
        }
        return sum;
    }


}
