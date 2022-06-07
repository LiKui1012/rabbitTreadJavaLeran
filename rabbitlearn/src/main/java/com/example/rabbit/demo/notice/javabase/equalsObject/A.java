package com.example.rabbit.demo.notice.javabase.equalsObject;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class A {
    public static void main(String[] args) {
      String a="SELECT\n" +
              "\tsum(temp.设备总数) AS '设备总数',\n" +
              "\tsum(temp.通道总数) AS '通道总数',\n" +
              "\tsum(temp.新增设备数) AS '新增设备数',\n" +
              "\tsum(temp.新增通道数) AS '新增通道数',\n" +
              "\tsum(temp.开启云存通道数) AS '开启云存通道数',\n" +
              "\ttemp.user_id AS '租户id',\n" +
              "\tsum(temp.设备在线总数) AS '设备在线总数',\n" +
              "\tsum(temp.设备离线总数) AS '设备离线总数',\n" +
              "\tsum(temp.通道在线总数) AS '通道在线总数',\n" +
              "\tsum(temp.通道离线总数) AS '通道离线总数',\n" +
              "\tsum(\n" +
              "\t\ttemp.开启云存在线通道数\n" +
              "\t) AS '开启云存在线通道数',\n" +
              "\tsum(\n" +
              "\t\ttemp.开启云存离线通道数\n" +
              "\t) AS '开启云存离线通道数'\n" +
              "FROM\n" +
              "\t(\n" +
              "\t\t(\n" +
              "\t\t\tSELECT\n" +
              "\t\t\t\tad.user_id,\n" +
              "\t\t\t\tcount(DISTINCT ad.device_id) AS '设备总数',\n" +
              "\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\tFROM\n" +
              "\t\t\t\taccess_device ad\n" +
              "\t\t\tGROUP BY\n" +
              "\t\t\t\tad.user_id\n" +
              "\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tcount(\n" +
              "\t\t\t\t\t\tDISTINCT adc.device_id,\n" +
              "\t\t\t\t\t\tadc.channel_id\n" +
              "\t\t\t\t\t) AS '通道总数',\n" +
              "\t\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device ad\n" +
              "\t\t\t\tLEFT JOIN access_device_channel adc ON ad.device_id = adc.device_id\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\t\tCOUNT(1) AS '新增设备数',\n" +
              "\t\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device ad\n" +
              "\t\t\t\tWHERE\n" +
              "\t\t\t\t\tDATE(ad.first_register_time) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\t\tcount(\n" +
              "\t\t\t\t\t\tDISTINCT adc.device_id,\n" +
              "\t\t\t\t\t\tadc.channel_id\n" +
              "\t\t\t\t\t) AS '新增通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device_channel adc\n" +
              "\t\t\t\tLEFT JOIN access_device ad ON adc.device_id = ad.device_id\n" +
              "\t\t\t\tWHERE\n" +
              "\t\t\t\t\tDATE(adc.create_time) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\t\tcount(\n" +
              "\t\t\t\t\t\tDISTINCT adc.device_id,\n" +
              "\t\t\t\t\t\tadc.channel_id\n" +
              "\t\t\t\t\t) AS '开启云存通道数',\n" +
              "\t\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device ad\n" +
              "\t\t\t\tLEFT JOIN access_device_channel adc ON ad.device_id = adc.device_id\n" +
              "\t\t\t\tWHERE\n" +
              "\t\t\t\t\tadc.hav_record = 1\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\t\tcount(DISTINCT ad.device_id) AS '设备在线总数',\n" +
              "\t\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device ad\n" +
              "\t\t\t\tWHERE\n" +
              "\t\t\t\t\tad.device_status = 1\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\t\tcount(DISTINCT ad.device_id) AS '设备离线总数',\n" +
              "\t\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device ad\n" +
              "\t\t\t\tWHERE\n" +
              "\t\t\t\t\tad.device_status = 0\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\t\tcount(\n" +
              "\t\t\t\t\t\tDISTINCT adc.device_id,\n" +
              "\t\t\t\t\t\tadc.channel_id\n" +
              "\t\t\t\t\t) AS '通道在线总数',\n" +
              "\t\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device ad\n" +
              "\t\t\t\tLEFT JOIN access_device_channel adc ON ad.device_id = adc.device_id\n" +
              "\t\t\t\tWHERE\n" +
              "\t\t\t\t\tadc.stream_status = 1\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\t\tcount(\n" +
              "\t\t\t\t\t\tDISTINCT adc.device_id,\n" +
              "\t\t\t\t\t\tadc.channel_id\n" +
              "\t\t\t\t\t) AS '通道离线总数',\n" +
              "\t\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device ad\n" +
              "\t\t\t\tLEFT JOIN access_device_channel adc ON ad.device_id = adc.device_id\n" +
              "\t\t\t\tWHERE\n" +
              "\t\t\t\t\tadc.stream_status = 0\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\t\tcount(\n" +
              "\t\t\t\t\t\tDISTINCT adc.device_id,\n" +
              "\t\t\t\t\t\tadc.channel_id\n" +
              "\t\t\t\t\t) AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device ad\n" +
              "\t\t\t\tLEFT JOIN access_device_channel adc ON ad.device_id = adc.device_id\n" +
              "\t\t\t\tWHERE\n" +
              "\t\t\t\t\tadc.hav_record = 1\n" +
              "\t\t\t\tAND adc.stream_status = 1\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t\tUNION ALL\n" +
              "\t\t\t(\n" +
              "\t\t\t\tSELECT\n" +
              "\t\t\t\t\tad.user_id,\n" +
              "\t\t\t\t\tNULL AS '设备总数',\n" +
              "\t\t\t\t\tNULL AS '通道总数',\n" +
              "\t\t\t\t\tNULL AS '新增设备数',\n" +
              "\t\t\t\t\tNULL AS '新增通道数',\n" +
              "\t\t\t\t\tNULL AS '开启云存通道数',\n" +
              "\t\t\t\t\tNULL AS '设备在线总数',\n" +
              "\t\t\t\t\tNULL AS '设备离线总数',\n" +
              "\t\t\t\t\tNULL AS '通道在线总数',\n" +
              "\t\t\t\t\tNULL AS '通道离线总数',\n" +
              "\t\t\t\t\tNULL AS '开启云存在线通道数',\n" +
              "\t\t\t\t\tcount(\n" +
              "\t\t\t\t\t\tDISTINCT adc.device_id,\n" +
              "\t\t\t\t\t\tadc.channel_id\n" +
              "\t\t\t\t\t) AS '开启云存离线通道数'\n" +
              "\t\t\t\tFROM\n" +
              "\t\t\t\t\taccess_device ad\n" +
              "\t\t\t\tLEFT JOIN access_device_channel adc ON ad.device_id = adc.device_id\n" +
              "\t\t\t\tWHERE\n" +
              "\t\t\t\t\tadc.hav_record = 1\n" +
              "\t\t\t\tAND adc.stream_status = 0\n" +
              "\t\t\t\tGROUP BY\n" +
              "\t\t\t\t\tad.user_id\n" +
              "\t\t\t)\n" +
              "\t) temp\n" +
              "GROUP BY\n" +
              "\ttemp.user_id";
        System.out.println(replaceAllBlank(a));

    }


    //去除所有空格
    public static String replaceAllBlank(String str) {
        String s = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            /*\n 回车(\u000a)
            \t 水平制表符(\u0009)
            \s 空格(\u0008)
            \r 换行(\u000d)*/
            Matcher m = p.matcher(str);
            s = m.replaceAll("");
        }
        return s;
    }

}
