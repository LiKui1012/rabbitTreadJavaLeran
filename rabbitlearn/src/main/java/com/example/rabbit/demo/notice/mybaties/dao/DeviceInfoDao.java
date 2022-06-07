package com.example.rabbit.demo.notice.mybaties.dao;

import com.example.rabbit.demo.notice.mybaties.model.DeviceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInfoDao {

    DeviceInfo getById(int id);
    List<DeviceInfo> findAll();
    List<DeviceInfo> getByDeviceId(String deviceId);
}
