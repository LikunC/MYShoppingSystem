package com.hualy.shopping.dao;

import com.hualy.shopping.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> selectAll();
}
