package com.hualy.shopping.dao;

import com.hualy.shopping.pojo.MmallProduct;

import java.util.List;

public interface MmallProductDao {
    //    查询所有商品
    public List<MmallProduct> findAll();

    //    根据id查找商品
    public MmallProduct findProductById(MmallProduct mmallProduct);

    public int delete(MmallProduct mmallProduct);

    public int update(MmallProduct mmallProduct);

    public int add(MmallProduct mmallProduct);
}
