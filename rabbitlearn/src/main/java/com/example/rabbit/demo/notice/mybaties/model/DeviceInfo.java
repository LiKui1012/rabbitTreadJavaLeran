package com.example.rabbit.demo.notice.mybaties.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors( chain = true )
public class DeviceInfo {
    private Integer id;
    private String deviceId;
    private Integer nodeType;
    private Integer userId;
    private Integer status;
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Timestamp用DateTimeFormat dateTime用JsonFormat
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",locale = "zh",timezone ="GMT+8")
    private Date createTime;
}
