package com.hualy.shopping.dao;

import com.hualy.shopping.pojo.MmallOrder;

import java.util.List;
import java.util.Map;

public interface MmallOrderDao {
    //    查找所有的订单
    public Map<String, Object> findAll();

    //    查找未支付订单
    public Map<String, Object> findUnpaidOrders(MmallOrder mmallOrder);

    //    查找未发货订单
    public Map<String, Object> findUnfilledOrders(MmallOrder mmallOrder);

    //    查找已发货订单
    public Map<String, Object> findDeliveredOrders(MmallOrder mmallOrder);

    //    查找已到货订单
    public Map<String, Object> findReceivedOrders(MmallOrder mmallOrder);

    //    添加订单
    public int add(MmallOrder mmallOrder);

    //    删除订单
    public int delete(MmallOrder mmallOrder);
}
