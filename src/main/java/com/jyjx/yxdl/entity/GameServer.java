package com.jyjx.yxdl.entity;

public class GameServer {

    private int serverId;
    private String serverName;
    private int appId;
    private int value;
    private String label;
    private int dbIndex;
    private int redisHostId;

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getRedisHostId() {
        return redisHostId;
    }

    public void setRedisHostId(int redisHostId) {
        this.redisHostId = redisHostId;
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "serverId=" + serverId +
                ", serverName='" + serverName + '\'' +
                ", appId=" + appId +
                ", value=" + value +
                ", label='" + label + '\'' +
                ", dbIndex=" + dbIndex +
                ", redisHostId=" + redisHostId +
                '}';
    }

}
