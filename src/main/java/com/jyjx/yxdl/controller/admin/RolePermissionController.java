package com.jyjx.yxdl.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.jyjx.yxdl.entity.RolePermission;
import com.jyjx.yxdl.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping(value = "admin/rolePermission")
public class RolePermissionController extends AdminBaseController{

    @Autowired
    public RolePermissionService rolePermissionService;

    @RequestMapping(path = "getRolePermission")
    public Map<String,Object> getRolePermission(){
        List<RolePermission> rolePermission = rolePermissionService.getRolePermission(null);
        List<RolePermission> permissionTree = rolePermissionService.getRolePermissionTree(rolePermission);
        return displaySuccess(permissionTree);
    }

    public static void main(String[] args) {
    }




}
