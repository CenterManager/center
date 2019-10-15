package com.jyjx.yxdl.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class Admin {

    private int adminId;
    private String adminAccount;
    private String adminPass;
    private short status;

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminAccount=" + adminAccount +
                ", adminPass=" + adminPass +
                ", status=" + status +
                '}';
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

}
