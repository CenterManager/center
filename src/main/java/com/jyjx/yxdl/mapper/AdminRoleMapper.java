package com.jyjx.yxdl.mapper;

import com.jyjx.yxdl.common.DataBaseType;
import com.jyjx.yxdl.common.DataSource;
import com.jyjx.yxdl.entity.AdminRole;

import java.util.List;

public interface AdminRoleMapper {

    @DataSource(name = DataBaseType.MASTER)
    public List<AdminRole> selectRoleByAdminId(int adminId);
    @DataSource(name = DataBaseType.MASTER)
    public List<AdminRole> getAdminRoleList(int start,int pageSize);
    @DataSource(name = DataBaseType.MASTER)
    public int getAdminRoleCount();
    @DataSource(name = DataBaseType.MASTER)
    public AdminRole findAdminRoleById(int adminRoleId);
    @DataSource(name = DataBaseType.MASTER)
    public List<AdminRole> findRoleListByAdminId(int adminId);
    @DataSource(name = DataBaseType.MASTER)
    public List<Integer> getRoleIdByAdminId(int adminId);
}
