package com.jyjx.yxdl.service;

import com.jyjx.yxdl.entity.AdminMenu;
import com.jyjx.yxdl.mapper.AdminMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AdminMenuService {

    @Autowired
    public AdminMenuMapper adminMenuMapper;
    @Autowired
    public AdminRoleService adminRoleService;
    @Autowired
    public RolePermissionService rolePermissionService;

    public List<AdminMenu> getAdminMenuByPrentId(int parentId){
        List<AdminMenu> menuList = adminMenuMapper.getAdminMenuByPrentId(parentId);
        return menuList;
    }

    public List<AdminMenu> getAdminMenuTree(List<AdminMenu> menuList,List<Integer> roleIds){
        for ( AdminMenu adminMenu  : menuList ){
            List<AdminMenu> childMenu = getAuthMenu(roleIds,adminMenu.getAdminMenuId());
            if(null != childMenu || childMenu.size() != 0 ){
                adminMenu.childMenu = childMenu;
                getAdminMenuTree(adminMenu.childMenu,roleIds);
            }

        }
        return menuList;
    }

    public Map<String,List> getAdminMenuByAdminId(int adminId){
        List<Integer> roleIds = adminRoleService.getRoleIdByAdminId(adminId);
        List<AdminMenu> authMenu = getAuthMenu(roleIds,null);
        Map<String,List> resMap = new HashMap<>();
        resMap.put("roleIds",roleIds);
        resMap.put("authMenu",authMenu);
        return resMap;
    }


    public List<AdminMenu> getAuthMenu(List<Integer> roleIds,Integer parentId){
        boolean contains = roleIds.contains(1);
        if(contains){
            return adminMenuMapper.getAuthMenu(null,parentId);
        }
        return adminMenuMapper.getAuthMenu(roleIds,parentId);
    }

    public static void main(String[] args) {

    }
    public static Object getMaxKey(Map<Integer, Integer> map) {
        if (map == null) return null;
        Set<Integer> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return obj[obj.length-1];
    }

}
