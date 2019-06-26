package com.hualy.shopping.dao;

import com.hualy.shopping.pojo.MmallOrder;

import java.util.List;
import java.util.Map;

public interface MmallOrderDao {
    //    查找所有的订单
    public List<Map<String, Object>> findAll();

    //将订单修改为已发货状态
    public int updataToShipped(MmallOrder mmallOrder);

    //将订单修改为交易成功状态
    public int updateToComplete(MmallOrder mmallOrder);

}
