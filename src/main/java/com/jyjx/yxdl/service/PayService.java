package com.jyjx.yxdl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.jyjx.yxdl.common.util.StringUtil;
import com.jyjx.yxdl.entity.Pay;
import com.jyjx.yxdl.mapper.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PayService {

    @Autowired
    public PayMapper payMapper;

    private static SerializeConfig mapping = new SerializeConfig();
    private static String dateFormat;
    static {
        dateFormat = "yyyy-MM-dd HH:mm:ss";
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
    }

    public List<Pay> getPayList(int page,int pageSize){
        int start = (page - 1) * pageSize;
        return payMapper.getPayList(start,pageSize);
    }

    public int getPayCount(){
        return payMapper.getPayCount();
    }

    public List<Map<String,Object>> getPayGroup(int page, int pageSize,Integer appId,Integer femaleBag,String intervalTimer){
        int start = (page - 1) * pageSize;
        if(StringUtil.isNotEmpty(intervalTimer)){
            String[] timerArr = StringUtil.split(intervalTimer,",");
            return payMapper.getPayGroup(start,pageSize,appId,femaleBag,timerArr[0],timerArr[1]);
        }
        return payMapper.getPayGroup(start,pageSize,appId,femaleBag,null,null);
    }

    public int getGroupCount(Integer appId,Integer femaleBag,String intervalTimer){
        if(StringUtil.isNotEmpty(intervalTimer)){
            String[] timerArr = StringUtil.split(intervalTimer,",");
            return payMapper.getGroupCount(appId,femaleBag,timerArr[0],timerArr[1]);
        }
        return payMapper.getGroupCount(appId,femaleBag,null,null);
    }

    public static String serialize(Object obj) {
        return JSON.toJSONString(obj, mapping);
    }

    public int getAllPayMoney(){
        return payMapper.getAllPayMoney();
    }
}
