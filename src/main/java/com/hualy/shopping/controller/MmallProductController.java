package com.hualy.shopping.controller;

import com.hualy.shopping.dao.MmallProductDao;
import com.hualy.shopping.pojo.MmallProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MmallProductController {
    @Autowired
    MmallProductDao mpdao;

    @RequestMapping(value = "/api/admin/product/list", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> findAll() {
        List<MmallProduct> list = mpdao.findAll();
        if (list != null && list.size() > 0) {
            return JSONResult(0, "查找成功", list);
        } else {
            return JSONResult(1, "暂无商品信息", null);
        }
    }

    @RequestMapping(value = "/api/admin/product", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> add(HttpServletRequest req, HttpServletResponse res) {
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        String name = req.getParameter("name");
        String subtitle = req.getParameter("subtitle");
        String main_image = req.getParameter("main_image");
        String subImage = req.getParameter("sub_image");
        String detail = req.getParameter("detail");
        float price = Float.parseFloat(req.getParameter("price"));
        int stock = Integer.parseInt(req.getParameter("stock"));
        String status = req.getParameter("status");

        MmallProduct mmallProduct = new MmallProduct();

        mmallProduct.setCategory_id(categoryId);
        mmallProduct.setName(name);
        mmallProduct.setSubtitle(subtitle);
        mmallProduct.setMain_image(main_image);
        mmallProduct.setSub_image(subImage);
        mmallProduct.setDetail(detail);
        mmallProduct.setPrice(price);
        mmallProduct.setStock(stock);
        mmallProduct.setStatus(status);
        Date date = new Date();
        DateFormat df = DateFormat.getDateTimeInstance();
        mmallProduct.setUpdate_time(df.format(date));
        mmallProduct.setCreate_time(df.format(date));
        int row = mpdao.add(mmallProduct);
        if (row > 0) {
            return JSONResult(0, "添加成功", mmallProduct);
        } else {
            return JSONResult(1, "添加失败", null);
        }
    }

    @RequestMapping(value = "/api/admin/product/update", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> update(HttpServletRequest req, HttpServletResponse res) {
        int id = Integer.parseInt(req.getParameter("id").trim());
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        System.out.println(categoryId);
        String name = req.getParameter("name");
        String subtitle = req.getParameter("subtitle");
        String main_image = req.getParameter("main_image");
        String subImage = req.getParameter("sub_image");
        String detail = req.getParameter("detail");
        float price = Float.parseFloat(req.getParameter("price"));
        int stock = Integer.parseInt(req.getParameter("stock"));
        String status = req.getParameter("status");

        MmallProduct mmallProduct = new MmallProduct();
        mmallProduct.setId(id);
        MmallProduct mp = mpdao.findProductById(mmallProduct);
        if (mp != null) {
            mp.setCategory_id(categoryId);
            mp.setName(name);
            mp.setSubtitle(subtitle);
            if (main_image != null) {
                mp.setMain_image(main_image);
            }
            if ( subImage != null) {
                if(mp.getSub_image()==null||mp.getSub_image().equals("")){
                    mp.setSub_image(subImage);
                }else{
                    mp.setSub_image(mp.getSub_image()+","+subImage);
                }

            }
            mp.setDetail(detail);
            mp.setPrice(price);
            mp.setStock(stock);
            mp.setStatus(status);
            Date date = new Date();
            DateFormat df2 = DateFormat.getDateTimeInstance();
            mp.setUpdate_time(df2.format(date));
            int row = mpdao.update(mp);
            if (row > 0) {
                return JSONResult(0, "修改成功", mp);
            } else {
                return JSONResult(1, "修改失败", null);
            }


        } else {
            return JSONResult(1, "修改失败", null);
        }

    }

    @RequestMapping(value = "/api/admin/product", method = RequestMethod.DELETE)
    @ResponseBody
    private Map<String, Object> delete(HttpServletRequest req, HttpServletResponse res) {
        int id = Integer.parseInt(req.getParameter("id").trim());
        System.out.println(id);
        MmallProduct mmallProduct = new MmallProduct();
        mmallProduct.setId(id);
        int rows = mpdao.delete(mmallProduct);
        if (rows > 0) {
            return JSONResult(0, "删除成功", rows);
        } else {
            return JSONResult(1, "删除失败", null);
        }
    }

    private Map<String, Object> JSONResult(int code, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);
        return result;
    }
}
