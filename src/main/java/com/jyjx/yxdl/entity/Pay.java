package com.jyjx.yxdl.entity;

public class Pay {

    private String id;
    private int app_id;
    private String channel;
    private String create_time;
    private String modify_time;
    private int money;
    private String notify_ip;
    private String notify_params;
    private String notify_time;
    private String notify_trans_id;
    private String role_id;
    private int server_id;
    private int state;
    private String subchannel;
    private int try_count;
    private String try_time;
    private String username;
    private int version;
    private String female_bag;
    private String price;
    private String states;

    @Override
    public String toString() {
        return "Pay{" +
                "id='" + id + '\'' +
                ", app_id=" + app_id +
                ", channel='" + channel + '\'' +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                ", money=" + money +
                ", notify_ip='" + notify_ip + '\'' +
                ", notify_params='" + notify_params + '\'' +
                ", notify_time='" + notify_time + '\'' +
                ", notify_trans_id='" + notify_trans_id + '\'' +
                ", role_id='" + role_id + '\'' +
                ", server_id=" + server_id +
                ", state=" + state +
                ", subchannel='" + subchannel + '\'' +
                ", try_count=" + try_count +
                ", try_time='" + try_time + '\'' +
                ", username='" + username + '\'' +
                ", version=" + version +
                ", female_bag='" + female_bag + '\'' +
                ", price='" + price + '\'' +
                ", states='" + states + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getNotify_ip() {
        return notify_ip;
    }

    public void setNotify_ip(String notify_ip) {
        this.notify_ip = notify_ip;
    }

    public String getNotify_params() {
        return notify_params;
    }

    public void setNotify_params(String notify_params) {
        this.notify_params = notify_params;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getNotify_trans_id() {
        return notify_trans_id;
    }

    public void setNotify_trans_id(String notify_trans_id) {
        this.notify_trans_id = notify_trans_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSubchannel() {
        return subchannel;
    }

    public void setSubchannel(String subchannel) {
        this.subchannel = subchannel;
    }

    public int getTry_count() {
        return try_count;
    }

    public void setTry_count(int try_count) {
        this.try_count = try_count;
    }

    public String getTry_time() {
        return try_time;
    }

    public void setTry_time(String try_time) {
        this.try_time = try_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getFemale_bag() {
        return female_bag;
    }

    public void setFemale_bag(String female_bag) {
        this.female_bag = female_bag;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

}
