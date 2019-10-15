package com.jyjx.yxdl.common;

import com.jyjx.yxdl.entity.Admin;
import com.jyjx.yxdl.entity.AdminRole;
import com.jyjx.yxdl.entity.RolePermission;
import com.jyjx.yxdl.service.AdminRoleService;
import com.jyjx.yxdl.service.AdminService;
import com.jyjx.yxdl.service.RolePermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;
public class ShiroRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    private String salt = "md5hashadmin";
    //这里尝试过使用@Autowired 但是发现会报错。这个是spring的注解。如果有知道原因的可以留言。谢谢
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    /**
     * 配置权限 注入权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        System.out.println("--------权限配置-------");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Admin admin = (Admin) principals.getPrimaryPrincipal();
        try {
            //注入角色(查询所有的角色注入控制器）
            List<AdminRole> list = adminRoleService.findRoleListByAdminId(admin.getAdminId());
            for (AdminRole role: list){
                authorizationInfo.addRole(role.getRoleName());
            }
            //注入角色所有权限（查询用户所有的权限注入控制器）
            List<RolePermission> sysAuths = rolePermissionService.selectPermissionByAdminId(admin.getAdminId());
            for(RolePermission rolePermission:sysAuths){
                authorizationInfo.addStringPermission(rolePermission.getOperation());
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("ShiroRealm : 角色或权限注入失败");
        }
        return authorizationInfo;
    }

    /**
     * 用户验证
     * @param token 账户数据
     * @return
     * @throws AuthenticationException 根据账户数据查询账户。根据账户状态抛出对应的异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号
        String adminAccount = (String) token.getPrincipal();
        //这里需注意。看别人的教程有人是这样写的String password = (String) token.getCredentials();
        //项目运行的时候报错，发现密码不正确。后来进源码查看发现将密码注入后。Shiro会进行转义将字符串转换成字符数组。
        //源码：this(username, password != null ? password.toCharArray() : null, false, null);
        //不晓得是否是因为版本的原因，建议使用的时候下载源码进行查看
        String password = new String((char[]) token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        Admin admin = adminService.findByAdminAccount(adminAccount);
        if(null == admin){
            throw new UnknownAccountException();
        }else {

            SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(admin,admin.getAdminPass().toCharArray(),ByteSource.Util.bytes(salt),getName());
            return authorizationInfo;
//            if(password.equals(admin.getAdminPass())){
//                if(0 == admin.getStatus()){
//                    throw new LockedAccountException();
//                }else if (2 == admin.getStatus()){
//                    throw new DisabledAccountException();
//                }else{
//                    SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(admin,admin.getAdminPass().toCharArray(),ByteSource.Util.bytes(salt),getName());
//                    return authorizationInfo;
//                }
//            } else {
//                throw new IncorrectCredentialsException();
//            }
        }

    }


}