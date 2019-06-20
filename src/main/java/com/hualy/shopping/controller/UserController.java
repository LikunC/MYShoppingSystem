package com.hualy.shopping.controller;

import com.hualy.shopping.dao.UserDao;
import com.hualy.shopping.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/user")
    @ResponseBody
    private Map<String, Object> getUser() {
        List<User> users = userDao.selectAll();
        return JSONResult(0, "success", users);
    }


    private Map<String, Object> JSONResult(int code, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);
        return result;
    }


}
