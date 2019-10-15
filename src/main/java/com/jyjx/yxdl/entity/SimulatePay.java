package com.jyjx.yxdl.entity;

public class SimulatePay {

    private int simulatePayId;
    private String role;
    private String payTime;
    private String serverName;
    private int money;

    @Override
    public String toString() {
        return "SimulatePay{" +
                "simulatePayId=" + simulatePayId +
                ", role='" + role + '\'' +
                ", payTime=" + payTime +
                ", serverName='" + serverName + '\'' +
                ", money='" + money + '\'' +
                '}';
    }

    public int getSimulatePayId() {
        return simulatePayId;
    }

    public void setSimulatePayId(int simulatePayId) {


        this.simulatePayId = simulatePayId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
