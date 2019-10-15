package com.jyjx.yxdl.service;

import com.jyjx.yxdl.entity.Admin;
import com.jyjx.yxdl.entity.AdminMenu;
import com.jyjx.yxdl.entity.AdminRole;
import com.jyjx.yxdl.entity.RolePermission;
import com.jyjx.yxdl.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionService {

    @Autowired
    public RolePermissionMapper rolePermissionMapper;
    @Autowired
    public AdminService adminService;
    @Autowired
    public AdminRoleService adminRoleService;

    public List<RolePermission> getRolePermission(Integer parentId){
        return rolePermissionMapper.getRolePermission(parentId);
    }

    public List<RolePermission> getRolePermissionTree(List<RolePermission> RolePermissionList){
        for ( RolePermission rolePermission  : RolePermissionList ){
            List<RolePermission> childPermission = this.getRolePermission(rolePermission.getRolePermissionId());
            if(null != childPermission || childPermission.size() != 0 ){
                rolePermission.setChildPermission(childPermission);
                getRolePermissionTree(rolePermission.getChildPermission());
            }

        }
        return RolePermissionList;
    }

    public List<RolePermission> selectPermissionByAdminId(int adminId){
        Admin admin =adminService.findAdminByPrimary(adminId);
        List<Integer> roleIds = adminRoleService.getRoleIdByAdminId(admin.getAdminId());
        if(roleIds.contains(1)){
            return rolePermissionMapper.getAllPermissionByRoleId(null);
        }
        return rolePermissionMapper.getAllPermissionByRoleId(roleIds);
    }





}
