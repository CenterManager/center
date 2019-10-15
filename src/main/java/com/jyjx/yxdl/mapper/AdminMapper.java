package com.jyjx.yxdl.mapper;

import com.jyjx.yxdl.common.DataBaseType;
import com.jyjx.yxdl.common.DataSource;
import com.jyjx.yxdl.entity.Admin;

public interface AdminMapper {

    @DataSource(name = DataBaseType.MASTER)
    public Admin findAdminByPrimary(int adminId);
    @DataSource(name = DataBaseType.MASTER)
    public Admin findAdminByAdminAccount(String adminAccount);

}
