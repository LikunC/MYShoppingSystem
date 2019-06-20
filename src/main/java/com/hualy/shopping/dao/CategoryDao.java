package com.hualy.shopping.dao;

import com.hualy.shopping.pojo.AdminUser;
import com.hualy.shopping.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryDao {

    public Category find(String id);

    public int add(Category category);

    public int edit(Category user);

    public int delete(String ids);

    public List<Category> findList(Map<String, Object> queryMap);

    public int getTotal(Map<String, Object> queryMap);
}
