package com.jyjx.yxdl.entity;

public class Redis {

    private int redisHostId;
    private String redisHost;
    private String redisNickName;

    public int getRedisHostId() {
        return redisHostId;
    }

    public void setRedisHostId(int redisHostId) {
        this.redisHostId = redisHostId;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public String getRedisNickName() {
        return redisNickName;
    }

    public void setRedisNickName(String redisNickName) {
        this.redisNickName = redisNickName;
    }

    @Override
    public String toString() {
        return "Redis{" +
                "redisHostId=" + redisHostId +
                ", redisHost='" + redisHost + '\'' +
                ", redisNickName='" + redisNickName + '\'' +
                '}';
    }

}
