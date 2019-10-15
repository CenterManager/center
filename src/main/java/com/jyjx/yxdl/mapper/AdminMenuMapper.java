package com.jyjx.yxdl.mapper;

import com.jyjx.yxdl.entity.AdminMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMenuMapper {

    public List<AdminMenu> getAdminMenuList(int adminMenuId);
    public List<AdminMenu> getAdminMenuByPrentId(@Param("parentId") int parentId);
    public List<AdminMenu> getAuthMenu(@Param("roleIds") List<Integer> roleIds,@Param("parentId") Integer parentId);

}
