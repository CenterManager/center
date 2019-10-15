package com.jyjx.yxdl.controller.admin;

import com.jyjx.yxdl.entity.App;
import com.jyjx.yxdl.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "admin/app")
public class AppController extends AdminBaseController{

    @Autowired
    public AppMapper appMapper;

    @RequestMapping(path = "getAllApp")
    public Map<String,Object> getAllApp(){
        List<App> appList = appMapper.findAllApp();
        return displaySuccess(appList);
    }

}
