package com.jyjx.yxdl.service;

import com.jyjx.yxdl.entity.Admin;
import com.jyjx.yxdl.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

@Service
public class AdminService {

    @Autowired
    public AdminMapper adminMapper;

    public Admin findAdminByPrimary(int adminId){
        return adminMapper.findAdminByPrimary(adminId);
    }

    public Admin findByAdminAccount(String adminAccount){
        return adminMapper.findAdminByAdminAccount(adminAccount);
    }

    public static void main(String[] args) {
            List<Admin> list = new ArrayList<>();
            getAdmin((t)-> t.getAdminId() == 2);
    }

    public static List<Admin> getAdmin(Predicate<Admin> pre){
        return new ArrayList<>();
    }

}
