package com.example.rabbit.demo.notice.mybaties.service;

import com.example.rabbit.demo.notice.mybaties.dao.DeviceInfoDao;
import com.example.rabbit.demo.notice.mybaties.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInfoService {
    @Autowired
    DeviceInfoDao deviceInfoDao;
    public DeviceInfo getById(int id){
        return deviceInfoDao.getById(id);
    }
    public List<DeviceInfo> getByDeviceId(String  deviceId){
        return deviceInfoDao.getByDeviceId(deviceId);
    }

    public List<DeviceInfo> findAll(){
        return deviceInfoDao.findAll();
    }
}
