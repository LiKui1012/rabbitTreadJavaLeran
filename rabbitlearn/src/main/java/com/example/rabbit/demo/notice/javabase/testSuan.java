package com.example.rabbit.demo.notice.javabase;

public class testSuan {
    public static void main(String[] args) {
        double monthPaly = 0;
        double yuelixiPaly = 0;
        double lixitotalPaly = 0;
        double benjin = 430000.00;
        //(1.0049的n次方)
        double mizongshu = 2959 / (2959 - benjin * 0.0049);
        double huankuanqishu = isPowerOfTwo(mizongshu);
        double totalmonthPaly = 0;
        //如果提前每四个月提前还1万
        int countTotal = 0;
        int tiqianTotal = 0;
        lixiAndPay(huankuanqishu, countTotal, tiqianTotal, monthPaly, yuelixiPaly, benjin, totalmonthPaly, lixitotalPaly);
    }

    //开根号得出还款期数
    public static double isPowerOfTwo(double n) {
        double temp = 1;
        int count = 0;
        while (temp <= n) {
            if (temp == n) {
                return count;
            }
            temp *= 1.0049;
            count++;
        }
        return count;
    }


    public static void lixiAndPay(double huankuanqishu, int countTotal, int tiqianTotal, double monthPaly,
                                  double yuelixiPaly, double benjin, double totalmonthPaly, double lixitotalPaly) {
        double yuelilv = 0.0049;
        countTotal++;
        System.out.println("==第" + countTotal + "次还款");
        if (huankuanqishu > 6) {
            if (countTotal % 4 != 0) {
                //1,2,3,5，6，7
                //都是按照固定A来还款 ，即使还了，期数的减少不影响A，只影响本金
                double A = Math.pow(1.0049, huankuanqishu);
                double B = A - 1;
                monthPaly = benjin * ((yuelilv * A) / B);
                yuelixiPaly = benjin * yuelilv;
                benjin = benjin - (monthPaly - yuelixiPaly);
                System.out.println("第" + countTotal + "次支付利息" + yuelixiPaly);
                System.out.println("第" + countTotal + "次支付本息" + monthPaly);
                totalmonthPaly = totalmonthPaly + monthPaly;
                lixitotalPaly = lixitotalPaly + yuelixiPaly;
                lixiAndPay(huankuanqishu, countTotal, tiqianTotal, monthPaly, yuelixiPaly, benjin, totalmonthPaly, lixitotalPaly);
            } else if (countTotal % 4 == 0) {
                //只有提前还款会影响期数
                tiqianTotal++;
                System.out.println("==第" + tiqianTotal + "次提前还款");
                benjin = benjin - 10000;
                double mizongshu = 2959 / (2959 - benjin * 0.0049);
                huankuanqishu = isPowerOfTwo(mizongshu);
                double A = Math.pow(1.0049, huankuanqishu);
                double B = A - 1;
                monthPaly = benjin * ((yuelilv * A) / B);
                yuelixiPaly = benjin * yuelilv;
                benjin = benjin - (monthPaly - yuelixiPaly);
                System.out.println("第" + countTotal + "次支付利息" + yuelixiPaly);
                System.out.println("第" + countTotal + "次支付本息" + monthPaly);
                totalmonthPaly = totalmonthPaly + monthPaly;
                lixitotalPaly = lixitotalPaly + yuelixiPaly;
                lixiAndPay(huankuanqishu, countTotal, tiqianTotal, monthPaly, yuelixiPaly, benjin, totalmonthPaly, lixitotalPaly);
            }
        } else {
            System.out.println("++++++++++++剩余期数" + huankuanqishu);
            System.out.println("++++++++++++剩余本金" + benjin);
            double tiqianbenjin=tiqianTotal*10000;
            totalmonthPaly=totalmonthPaly+tiqianbenjin+benjin;
            System.out.println("++++++++++++总支付" + totalmonthPaly);
            System.out.println("++++++++++++总利息" + lixitotalPaly);
        }
    }
}
