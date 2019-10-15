package com.jyjx.yxdl.controller.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @RequestMapping(value = "index")
    public String index(){
        return "admin/index";
    }

    @RequestMapping(value = "indexP")
    public String indexP(){
        return "admin/indexP";
    }

    @RequiresPermissions("/admin/pay/list")
    @RequestMapping(value = "pay/list")
    public String payList(){
        return "admin/pay/list";
    }

    @RequiresPermissions("/admin/pay/group")
    @RequestMapping(value = "pay/group")
    public String payGroup(){
        return "admin/pay/group";
    }

    @RequiresPermissions("/admin/pay/simulatePay")
    @RequestMapping(value = "pay/simulatePay")
    public String simulatePay(){ return "admin/pay/simulatePay"; }

    @RequiresPermissions("/admin/pay/simulateList")
    @RequestMapping(value = "pay/simulateList")
    public String simulateList(){ return "admin/pay/simulateList"; }

}
