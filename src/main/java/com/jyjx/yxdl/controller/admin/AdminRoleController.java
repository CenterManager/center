package com.jyjx.yxdl.controller.admin;

import com.jyjx.yxdl.entity.Admin;
import com.jyjx.yxdl.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(path = "admin/adminRole")
public class AdminRoleController extends AdminBaseController{

    @Autowired
    public AdminRoleService adminRoleService;

    @RequestMapping(path = "getAdminRoleList")
    public Map<String,Object> getAdminRoleList(@RequestParam(value = "page") int page,@RequestParam(value = "pageSize") int pageSize){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("list",adminRoleService.getAdminRoleList(page,pageSize));
        dataMap.put("count",adminRoleService.getAdminRoleCount());
        return displaySuccess(dataMap);
    }

    @RequestMapping(path = "adminRoleEdit")
    public Map<String,Object> adminRoleEdit(@RequestParam(value = "adminRoleId") int adminRoleId){
        return displaySuccess(adminRoleService.findAdminRoleById(adminRoleId));
    }

    public static int jianfa(int a){
        return a-1;
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(1,1);
    }

}
