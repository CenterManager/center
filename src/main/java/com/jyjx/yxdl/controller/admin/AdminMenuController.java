package com.jyjx.yxdl.controller.admin;

import com.jyjx.yxdl.entity.Admin;
import com.jyjx.yxdl.entity.AdminMenu;
import com.jyjx.yxdl.service.AdminMenuService;
import com.jyjx.yxdl.service.AdminRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "adminMenu")
public class AdminMenuController {

    @Autowired
    public AdminMenuService adminMenuService;
    @Autowired
    public AdminRoleService adminRoleService;

    @RequestMapping(path = "getAdminMenuList")
    public Map<String,Object> getAdminMenuList(){
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        Map<String,List> resMap = adminMenuService.getAdminMenuByAdminId(admin.getAdminId());
        List<AdminMenu> menuTree = adminMenuService.getAdminMenuTree(resMap.get("authMenu"),resMap.get("roleIds"));
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("stateCode",1);
        resultMap.put("data",menuTree);
        return resultMap;
    }
}
