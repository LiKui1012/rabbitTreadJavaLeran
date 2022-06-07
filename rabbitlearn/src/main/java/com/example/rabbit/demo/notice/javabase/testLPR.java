package com.example.rabbit.demo.notice.javabase;

public class testLPR {
    public static void main(String[] args) {
        double monthPaly=0;
        double yuelixiPaly=0;
        double lixitotalPaly=0;

        double benjin=430000.00;
        double shengyubenjin=430000.00;


        double yuelilv=0.0049;
        double totalmonthPaly=0;

        //计数
        int count=0;
        //(1.0049的n次方)
        double mizongshu=0;
        mizongshu=2959/(2959-benjin*0.0049);
        double huankuanqishu=isPowerOfTwo(mizongshu);
        //正常还
        while (huankuanqishu>0){
            huankuanqishu--;
            double A=Math.pow(1.0049,255);
            double B=A-1;
            monthPaly=430000.00*((yuelilv*A)/B);
            yuelixiPaly=benjin*yuelilv;
            benjin=benjin-(monthPaly-yuelixiPaly);
            System.out.println("每月支付利息"+yuelixiPaly);
            System.out.println("每月支付总额"+monthPaly);
            totalmonthPaly=totalmonthPaly+monthPaly;
            lixitotalPaly=lixitotalPaly+yuelixiPaly;
            count++;
            if(count>254){
                System.out.println(
                        "++++++++++++++++++++++++++++++总数"+count
                );
            }
        }
        System.out.println("2023年开始不提前还款支付总额"+totalmonthPaly);
        System.out.println("2023年开始不提前还款利息总额"+lixitotalPaly);

    }

    public static double isPowerOfTwo(double n) {
        double temp = 1;
        int count=0;
        while (temp<=n){
            if(temp==n){
                return count;
            }
            temp*=1.0049;
            count++;
        }
        return count;
    }
}
