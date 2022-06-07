package com.example.rabbit.demo.notice.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class testUtils {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String yesterdayTime = format.format(cal.getTime());
        System.out.println(
                "yesterdayTime"+yesterdayTime
        );
        LocalDateTime localDateTime = DateTimeUtils.stringToDateTime(yesterdayTime, "yyyy-MM-dd");
        System.out.println(
                "localDateTime"+localDateTime
        );
    }
}
