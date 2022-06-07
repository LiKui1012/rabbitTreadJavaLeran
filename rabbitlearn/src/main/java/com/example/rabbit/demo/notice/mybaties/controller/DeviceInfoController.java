package com.example.rabbit.demo.notice.mybaties.controller;

import com.example.rabbit.demo.notice.mybaties.model.DeviceInfo;
import com.example.rabbit.demo.notice.mybaties.service.DeviceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
//springboot 集成mybaties  用mysql
//1)引入jdbc，mybaties，驱动，build 加入 xml打包文件
//2)dao和xml放在同一位置，xml又叫mapper 映射
//3)启动类配置mapperScan，yaml配置数据库，model，xml文件地址
public class DeviceInfoController {
    @Autowired
    private DeviceInfoService deviceInfoService;

    @GetMapping(path = "getById")
    public DeviceInfo getById(String id) {
        System.out.println("1111"
        );
        return deviceInfoService.getById(Integer.parseInt(id));
    }

    @GetMapping(path = "getByDeviceId")
    public List<DeviceInfo> getByDeviceId(String deviceId) {
        return deviceInfoService.getByDeviceId(deviceId);
    }



    @GetMapping(path = "total")
    public List<DeviceInfo> statistics(String id) {
        //先查，查出来做递归增加
        List<DeviceInfo> DeviceInfos = deviceInfoService.findAll();

        //昨天第一次上线的状态，不用判断前天了，因为昨天如果第一次是上线，前天
        //最后一次必然是下线
        Boolean yesrterdayFirOnlineStatus=true;
        //把相同设备id的数据归类
        List<String> deviceIdList = DeviceInfos.stream().map(e -> e.getDeviceId()).collect(Collectors.toList());
        //去重
        deviceIdList = deviceIdList.stream().distinct().collect(Collectors.toList());
        if(CollectionUtils.isEmpty(deviceIdList)){
            //如果昨天数据为空，将前天统计的状态不变，上线下线改为100%
        }else{
            //如果昨天有数据
            //1.先分组
            for(String deviceId:deviceIdList){
                List<DeviceInfo> DeviceInfos1 = deviceInfoService.getByDeviceId(deviceId);
                //判断昨天第一条的数据状态
                long onlineTime=0;//540秒
                long outlineTime=0;//120秒
                if(!CollectionUtils.isEmpty(DeviceInfos1)){
                    if(DeviceInfos1.get(0).getStatus().equals(1)){
                        //1.1如果为在线
                    }
                    for(int i=0;i<DeviceInfos1.size();i++){



                    }
                }

            }



        }



        for(DeviceInfo d:DeviceInfos){
            //先判断昨天入库第一次变更的状态
//            if(){
//
//            }



        }
        return deviceInfoService.findAll();
    }

    //计算两个时间相差的秒数
    public static long getTime(String startTime, String endTime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long eTime = df.parse(endTime).getTime();
        long sTime = df.parse(startTime).getTime();
        long diff = (eTime - sTime) / 1000;
        return diff;
    }


}
