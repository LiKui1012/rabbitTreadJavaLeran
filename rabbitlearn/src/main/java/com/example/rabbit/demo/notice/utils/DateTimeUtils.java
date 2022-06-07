package com.example.rabbit.demo.notice.utils;

import com.sun.istack.internal.NotNull;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 2 * @Author: Yanweiwei
 * 3 * @Date: 2019/8/16 16:09
 * 4
 */
public class DateTimeUtils {

    /**
     * 根据时间获取时间戳
     *
     * @param dateTime
     * @return
     */
    public static Long dateTimeToTimestamp(LocalDateTime dateTime) {

        if (dateTime == null) {
            return null;
        }
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 根据时间戳获取日期时间
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToDateTime(Long timestamp) {
        return timestamp == null ? null : LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
    }


    /**
     * 获取指定格式的日期字符串
     *
     * @param dateTime
     * @param format   为null或者空时默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateTimeToString(@NotNull LocalDateTime dateTime, String format) {
        //时间转字符串格式化

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.SIMPLIFIED_CHINESE);
        return dateTime.format(formatter);
    }

    public static String dateToString(Date date, String format) {
        //时间转字符串格式化
        if (date == null) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }


    /**
     * 根据日期时间字符串获取日期时间
     *
     * @param datetime
     * @param format   为null或者空时默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime stringToDateTime(@NotNull String datetime, String format) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(datetime, formatter);
    }

    /**
     * 根据日期时间字符串获取mongo查询日期时间
     *
     * @param datetime
     * @param format   为null或者空时默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime stringToMongoDateTime(@NotNull String datetime, String format) {

        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.parse(datetime, formatter);
        return dateTime.minusHours(8);
    }

    /**
     * 根据日期时间字符串获取mongo查询日期时间
     *
     * @param datetime
     * @param format   为null或者空时默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime mongoDateTimeToString(String datetime, String format) {

        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.parse(datetime, formatter);
        return dateTime.minusHours(8);
    }


    /**
     * LocalDateTime ==> Date
     *
     * @param localDateTime
     * @return java.util.Date
     * @author long.chen
     * @date 2019/8/24 16:01
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * Date ==> LocalDateTime
     *
     * @param date
     * @return java.time.LocalDateTime
     * @author long.chen
     * @date 2019/8/24 16:01
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 字符串转date
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static Date stringToData(@NotNull String dateTime, String format) {
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        Date date = null;
        try {
            date = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 获取时间段内所有时间
     *
     * @param startTime:
     * @param endTime:
     * @param type:
     * @author: zaf
     * @date: 2021/10/15
     * @return: java.util.List<java.lang.String>
     */
    public static List<String> findDates(String startTime, String endTime, String type) {
        String date = "yyyy-MM-dd";
        if (type.equalsIgnoreCase("2")) {
            date = "yyyy-MM";
        }
        List<String> lDate = new ArrayList();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(date);
            Date dBegin = sdf.parse(startTime);
            Date dEnd = sdf.parse(endTime);
            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd);
            // 此日期是否在指定日期之后
            int times = Calendar.DAY_OF_MONTH;
            if (type.equalsIgnoreCase("2")) {
                times = Calendar.MONTH;
            }
            while (dEnd.after(calBegin.getTime())) {
                lDate.add(sdf.format(calBegin.getTime()));
                calBegin.add(times, 1);
            }
            lDate.add(sdf.format(calEnd.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lDate;
    }

    /**
     * 获取startTime~endTime内所有小时时间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> findHourDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<String> allDate = new ArrayList<>();
        try {
            Date dBegin = sdf.parse(startTime);
            Date dEnd = sdf.parse(endTime);
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(dBegin);

            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(dEnd);
            SimpleDateFormat sdff = new SimpleDateFormat("MM/dd HH:mm");
            while (dEnd.after(calBegin.getTime())) {
                allDate.add(sdff.format(calBegin.getTime()));
                calBegin.add(Calendar.HOUR_OF_DAY, 1);
            }
            allDate.add(sdff.format(calEnd.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allDate;
    }

    /**
     * mongoTime转回标准时间
     *
     * @param mongoTime
     * @return
     */
    public static String mongoTimeToString(String mongoTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dBegin = sdf.parse(mongoTime);
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(dBegin);
            calBegin.add(Calendar.HOUR_OF_DAY, 8);

            SimpleDateFormat sdff = new SimpleDateFormat("MM/dd HH:mm");
            return sdff.format(calBegin.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时分秒毫秒转毫秒
     *
     * @param time 时分秒毫秒
     * @return 毫秒
     */
    public static long getTimeLong(String time) {
        String dateTimeFrom = "2012-10-01 00:00:00.000";
        Calendar calendarFrom = Calendar.getInstance();
        try {
            calendarFrom.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(dateTimeFrom));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dateTime = "2012-10-01 " + time;
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar.getTimeInMillis() - calendarFrom.getTimeInMillis();
    }

    /**
     * 毫秒转时分秒毫秒
     *
     * @param time 毫秒
     * @return 时分秒毫秒
     */
    public static String getTimeString(long time) {
        String dateTimeFrom = "2012-10-01 00:00:00.000";
        Calendar calendarFrom = Calendar.getInstance();
        try {
            calendarFrom.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(dateTimeFrom));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        time = calendarFrom.getTimeInMillis() + time;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String temp = formatter.format(new Date(time));

        return temp.replaceAll("2012-10-01 ", "");
    }
}
