package com.jyjx.yxdl.mapper;

import com.jyjx.yxdl.entity.AdminMenu;
import com.jyjx.yxdl.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {

    public List<RolePermission> getRolePermission(Integer parentId);
    public List<RolePermission> selectPermissionByAdminId(int adminId);
    public List<RolePermission> getAllPermission();
    public List<RolePermission> getAllPermissionByRoleId(@Param("roleIds") List<Integer> roleIds);



}
