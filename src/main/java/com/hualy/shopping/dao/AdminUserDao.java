package com.hualy.shopping.dao;

import com.hualy.shopping.pojo.AdminUser;

import java.util.List;
import java.util.Map;

public interface AdminUserDao {
    public AdminUser findByUsername(String username);

    public AdminUser findById(Long id);

    public int add(AdminUser user);

    public int edit(AdminUser user);

    public int editPassword(AdminUser user);

    public int delete(String ids);

    public List<AdminUser> findList(Map<String, Object> queryMap);

    public int getTotal(Map<String, Object> queryMap);
}
