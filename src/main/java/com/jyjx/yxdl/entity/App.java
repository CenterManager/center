package com.jyjx.yxdl.entity;

public class App {

    private int appId;
    private String appName;
    private int value;
    private String label;

    @Override
    public String toString() {
        return "App{" +
                "appId=" + appId +
                ", appName='" + appName + '\'' +
                ", value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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
}
