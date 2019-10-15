package com.jyjx.yxdl.mapper;

import com.jyjx.yxdl.entity.SimulatePay;

import java.util.Date;
import java.util.List;

public interface SimulatePayMapper {

    public int addSimulatePay(String role, Date date, String serverName,int money,String orderId);
    public List<SimulatePay> getSimulateList(int start, int pageSize);
    public int getSimulateCount();

}
