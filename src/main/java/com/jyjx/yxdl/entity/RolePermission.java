package com.jyjx.yxdl.entity;

import java.util.List;

public class RolePermission {

    private int rolePermissionId;
    private String permissionName;
    private String operation;
    private String createTime;

    public List<RolePermission> getChildPermission() {
        return childPermission;
    }

    public void setChildPermission(List<RolePermission> childPermission) {
        this.childPermission = childPermission;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "rolePermissionId=" + rolePermissionId +
                ", permissionName='" + permissionName + '\'' +
                ", operation='" + operation + '\'' +
                ", createTime='" + createTime + '\'' +
                ", childPermission=" + childPermission +
                '}';
    }

    private List<RolePermission> childPermission;

    public int getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(int rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
