package com.jyjx.yxdl.entity;

public class AdminRole {

    private int adminRoleId;
    private String roleName;
    private String rolePermission;
    private short status;

    @Override
    public String toString() {
        return "AdminRole{" +
                "adminRoleId=" + adminRoleId +
                ", roleName='" + roleName + '\'' +
                ", rolePermission='" + rolePermission + '\'' +
                ", status=" + status +
                '}';
    }

    public int getAdminRoleId() {
        return adminRoleId;
    }

    public void setAdminRoleId(int adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRolePermission() {
        return rolePermission;
    }

    public void setRolePermission(String rolePermission) {
        this.rolePermission = rolePermission;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
