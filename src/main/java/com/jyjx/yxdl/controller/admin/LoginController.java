package com.jyjx.yxdl.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(path = "admin")
public class LoginController extends AdminBaseController{

    private final int LOGIN_PASS_ERROR = 410;
    private final int LOGIN_REST_ERROR = 410;
    private final String LOGIN_PASS_ERROR_MSG = "密码错误";
    private final String LOGIN_REST_ERROR_MSG = "登录失败";

    @RequestMapping(path = "login")
    public String login(){
        return "admin/login";
    }

    @RequestMapping(path = "loginCheck",method = RequestMethod.POST)
    @ResponseBody
    public Object loginCheck(@RequestParam(value = "adminAccount") String adminAccount , @RequestParam(value = "adminPass") String adminPass){
        UsernamePasswordToken token = new UsernamePasswordToken(adminAccount,adminPass);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return SUCCESS_STATUS;
        } catch (UnknownAccountException e) {
            Map resultMap = new HashMap();
            resultMap.put("status",LOGIN_PASS_ERROR);
            resultMap.put("msg",LOGIN_PASS_ERROR_MSG);
            return resultMap;
        } catch (IncorrectCredentialsException e) {
            Map resultMap = new HashMap();
            resultMap.put("status",LOGIN_PASS_ERROR);
            resultMap.put("msg",LOGIN_PASS_ERROR_MSG);
            return resultMap;
        } catch (ExcessiveAttemptsException e) {
            Map resultMap = new HashMap();
            resultMap.put("status",LOGIN_REST_ERROR);
            resultMap.put("msg",LOGIN_REST_ERROR_MSG);
            return resultMap;
        } catch (AuthenticationException e) {
            Map resultMap = new HashMap();
            resultMap.put("status",LOGIN_REST_ERROR);
            resultMap.put("msg",LOGIN_REST_ERROR_MSG);
            return resultMap;
        }

    }

}
