package com.hualy.shopping.controller;


import com.hualy.shopping.dao.CategoryDao;
import com.hualy.shopping.pojo.AdminUser;
import com.hualy.shopping.pojo.Category;
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
public class CategoryController {

    @Autowired CategoryDao dao;

    @RequestMapping(value = "/api/admin/category", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addCategory(HttpServletRequest req, HttpServletResponse res) {
        Category category = new Category();
        String name = req.getParameter("name");
        category.setName(name);
        dao.add(category);
        return JSONResult(0, "", category);
    }

    @RequestMapping(value = "/api/admin/category", method = RequestMethod.PUT)
    @ResponseBody
    private Map<String, Object> updateCategory(HttpServletRequest req, HttpServletResponse res) {
        Category category = new Category();
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        category.setId(Integer.valueOf(id));
        category.setName(name);
        dao.edit(category);
        return JSONResult(0, "", category);
    }


    @RequestMapping(value = "/api/admin/category", method = RequestMethod.DELETE)
    @ResponseBody
    private Map<String, Object> deleteCategory(HttpServletRequest req, HttpServletResponse res, int id) {
        Category category = new Category();
//        String id = req.getParameter("id");
        //category.setId(Integer.valueOf(id));
        dao.delete(id+"");
        return JSONResult(0, "", category);
    }

    @RequestMapping(value = "/api/admin/category", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getCategory(HttpServletRequest req, HttpServletResponse res, String id) {
        Category category = dao.find(id);
        return JSONResult(0, "", category);
    }

    @RequestMapping(value = "/api/admin/category/list", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getCategoryList(HttpServletRequest req, HttpServletResponse res, String id) {
        Map<String, Object> map = new HashMap<>();
        List<Category > categoryList = dao.findList(map);
        return JSONResult(0, "", categoryList);
    }

    private Map<String, Object> JSONResult(int code, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);
        return result;
    }

}
