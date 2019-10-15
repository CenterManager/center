package com.jyjx.yxdl.mapper;

import com.jyjx.yxdl.common.DataBaseType;
import com.jyjx.yxdl.common.DataSource;
import com.jyjx.yxdl.entity.Pay;

import java.util.List;
import java.util.Map;

public interface PayMapper {

    @DataSource(name = DataBaseType.SLAVE)
    public List<Pay> getPayList(int start,int pageSize);

    @DataSource(name = DataBaseType.SLAVE)
    public int getPayCount();

    @DataSource(name = DataBaseType.SLAVE)
    public List<Map<String,Object>> getPayGroup(int start,int pageSize,Integer appId,Integer femaleBag,String begin,String end);

    @DataSource(name = DataBaseType.SLAVE)
    public int getGroupCount(Integer appId,Integer femaleBag,String begin,String end);

    @DataSource(name = DataBaseType.SLAVE)
    public int getAllPayMoney();

}
