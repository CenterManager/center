package com.jyjx.yxdl.service;

import com.jyjx.yxdl.entity.AdminRole;
import com.jyjx.yxdl.mapper.AdminRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRoleService {

    @Autowired
    public AdminRoleMapper adminRoleMapper;

    public List<AdminRole> findRoleListByAdminId(int adminId){
        return adminRoleMapper.findRoleListByAdminId(adminId);
    }

    public List<AdminRole> getAdminRoleList(int page,int pageSize){
        int start = (page-1) * pageSize;
        return adminRoleMapper.getAdminRoleList(start,pageSize);
    }

    public int getAdminRoleCount(){
        return adminRoleMapper.getAdminRoleCount();
    }

    public AdminRole findAdminRoleById(int adminRoleId){
        return adminRoleMapper.findAdminRoleById(adminRoleId);
    }

    public List<Integer> getRoleIdByAdminId(int adminId){
        return adminRoleMapper.getRoleIdByAdminId(adminId);
    }
}
