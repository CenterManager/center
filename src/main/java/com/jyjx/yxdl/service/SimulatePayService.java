package com.jyjx.yxdl.service;

import com.jyjx.yxdl.entity.SimulatePay;
import com.jyjx.yxdl.mapper.SimulatePayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SimulatePayService {

    @Autowired
    public SimulatePayMapper simulatePayMapper;

    public List<SimulatePay> getSimulateList(int page ,int pageSize){
        int start = (page-1) * pageSize;
        return simulatePayMapper.getSimulateList(start,pageSize);
    }

    public int addSimulatePay(String role, Date date, String serverName,int money,String orderId){
        return simulatePayMapper.addSimulatePay(role,date,serverName,money,orderId);
    }

    public int getSimulateCount(){
        return simulatePayMapper.getSimulateCount();
    }
}
