package com.hualy.shopping.controller;


import com.hualy.shopping.dao.AdminUserDao;
import com.hualy.shopping.pojo.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminUserController {


    @Autowired
    AdminUserDao dao;


    @RequestMapping(value = "/api/admin/user",method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getUser(HttpServletRequest req) {
        Object userIdObj = req.getSession().getAttribute("user");
        if (userIdObj != null) {
            AdminUser user = dao.findById((Long) userIdObj);
            return JSONResult(0, "success", user);
        }
        return JSONResult(1, "error", null);

    }

    @RequestMapping(value = "/api/admin/login", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> login(HttpServletRequest req, HttpServletResponse res) {
        // 表单
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        AdminUser user = dao.findByUsername(username);
        if (user != null) {
            System.out.println("user = " + user.toString());
            if (user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user.getId());
                return JSONResult(0, "用户登录成功", user);
            } else {
                return JSONResult(1, "用户登录失败，密码错误", null);
            }

        } else {
            return JSONResult(1, "用户登录失败，用户名不存在", null);
        }
    }

    @RequestMapping(value = "/api/admin/check/login", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> checkLogin(HttpServletRequest req, HttpServletResponse res) {
        Object userIdObj = req.getSession().getAttribute("user");
        if (userIdObj != null) {
            return JSONResult(0, "用户已登录过", null);
        } else {
            return JSONResult(1, "用户未登录", null);
        }
    }
    @RequestMapping(value = "/api/admin/Users/list", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getUserList(HttpServletRequest req, HttpServletResponse res, String id) {
        Map<String, Object> map = new HashMap<>();
        List<AdminUser> userList = dao.findList(map);
        return JSONResult(0, "", userList);
    }

    @RequestMapping(value = "/api/admin/upDataUser", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> upDataUser(HttpServletRequest req, HttpServletResponse res) {
        Object userIdObj = req.getSession().getAttribute("user");
        AdminUser user = new AdminUser();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String photo = req.getParameter("photo");
        System.out.println(photo+"+++++++++++++++");
        Integer age =Integer.parseInt(req.getParameter("age"));
        String address = req.getParameter("address");
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setAge(age);
        user.setPhoto(photo);
        user.setId((Long) userIdObj);
        dao.edit(user);
        return JSONResult(0, "", user);
    }


    private Map<String, Object> JSONResult(int code, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);
        return result;
    }
}
