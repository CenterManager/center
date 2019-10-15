package com.jyjx.yxdl.mapper;

import com.jyjx.yxdl.entity.PayProduct;

import java.util.List;

public interface PayProductMapper {

    public List<PayProduct> getAllPayProduct();
    public PayProduct findByMoney(int money);

}
