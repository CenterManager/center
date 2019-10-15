package com.jyjx.yxdl.service;

import com.jyjx.yxdl.entity.Redis;
import com.jyjx.yxdl.mapper.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    public RedisMapper redisMapper;

    public List<Redis> getAllRedisHost(){
        return redisMapper.getAllRedisHost();
    }

    public Redis getRedisInfo(int redisHostId){
        return redisMapper.getRedisInfo(redisHostId);
    }

}
