package com.example.rabbit.demo.notice.redisserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//连接redis服务，并且打开redis查看有没有塞入值
@RestController
@Slf4j
public class RedisTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping("/name")
    public String  get(){
        this.stringRedisTemplate.opsForValue().set("name","英雄");
        this.stringRedisTemplate.opsForValue().set("java","javac");
        this.stringRedisTemplate.opsForList().leftPushAll("names","李四","王五","赵六");
        List listNames = this.stringRedisTemplate.opsForList().range("names", 0, 3);

        Map<String, String> map = new HashMap<>(4);
        map.put("name","秦始皇");
        map.put("age","未知");
        map.put("sex","男");
        this.stringRedisTemplate.opsForHash().putAll("people",map);
        String name =(String) this.stringRedisTemplate.opsForHash().get("people", "name");
        String age =(String) this.stringRedisTemplate.opsForHash().get("people", "age");
       log.info("listNames={}",listNames);
        log.info("age={}",age);
        log.info("name={}",name);
        return this.stringRedisTemplate.opsForValue().get("name");
    }
}
