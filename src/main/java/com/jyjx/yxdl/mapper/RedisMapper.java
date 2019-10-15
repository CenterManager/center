package com.jyjx.yxdl.mapper;

import com.jyjx.yxdl.entity.Redis;

import java.util.List;

public interface RedisMapper {

    public List<Redis> getAllRedisHost();
    public Redis getRedisInfo(int redisHostId);

}
