package com.jyjx.yxdl.entity;

import java.util.List;

public class AdminMenu {

    private int adminMenuId;
    private String adminMenuName;
    private String action;
    private int parentId;
    private short status;
    public List<AdminMenu> childMenu;

    @Override
    public String toString() {
        return "AdminMenu{" +
                "adminMenuId=" + adminMenuId +
                ", adminMenuName='" + adminMenuName + '\'' +
                ", action='" + action + '\'' +
                ", parentId=" + parentId +
                ", status=" + status +
                '}';
    }

    public int getAdminMenuId() {
        return adminMenuId;
    }

    public void setAdminMenuId(int adminMenuId) {
        this.adminMenuId = adminMenuId;
    }

    public String getAdminMenuName() {
        return adminMenuName;
    }

    public void setAdminMenuName(String adminMenuName) {
        this.adminMenuName = adminMenuName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
