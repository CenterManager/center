package com.jyjx.yxdl.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyjx.yxdl.common.util.HttpUtil;
import com.jyjx.yxdl.common.util.Md5;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class OrderInfoService {

    public String createOrder(String appId,String channel,String serverid,String username,String roleid,String money){
        String orderId = null;
        //api url地址
        String url = "http://yxdl.jiayijituan.cn:8100/paycenter/take/control.jsp";
        try {
            CloseableHttpClient client = null;
            HttpResponse response = null;
            try {
                List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
                pairList.add(new BasicNameValuePair("appid",appId));
                pairList.add(new BasicNameValuePair("channel",channel));
                pairList.add(new BasicNameValuePair("serverid",serverid));
                pairList.add(new BasicNameValuePair("username",username));
                pairList.add(new BasicNameValuePair("roleid",roleid));
                pairList.add(new BasicNameValuePair("money",money));
                HttpPost httpPost = new HttpPost(url);
                httpPost.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
                client = new DefaultHttpClient();
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                orderId = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public String notifyOrder(String orderId){
        long time = System.currentTimeMillis();
        String timeStr = String.valueOf(time);
        String sign = null;
        String result = null;
        try {
            sign = Md5.getMD5(orderId+timeStr+"jiayijituan.888");   //订单ID+当前时间戳+salt 生成sign
        } catch (Exception e) {
            e.printStackTrace();
        }
        //api url地址
        String url = "http://yxdl.jiayijituan.cn:8100/paycenter/to/GmNotify.jsp";
        try {
            CloseableHttpClient client = null;
            HttpResponse response = null;
            try {
                List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
                pairList.add(new BasicNameValuePair("orderId",orderId));
                pairList.add(new BasicNameValuePair("time",timeStr));
                pairList.add(new BasicNameValuePair("sign",sign));
                HttpPost httpPost = new HttpPost(url);
                httpPost.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
                client = new DefaultHttpClient();
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

