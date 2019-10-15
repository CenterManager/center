package com.jyjx.yxdl.controller.admin;


import com.jyjx.yxdl.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "admin/redis")
public class RedisController {

    @Autowired
    public RedisService redisService;

    @GetMapping(value = "getAllRedisHost")
    public Object getAllRedisHost(){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("stateCode",1);
        resultMap.put("data",redisService.getAllRedisHost());
        return resultMap;
    }

}
