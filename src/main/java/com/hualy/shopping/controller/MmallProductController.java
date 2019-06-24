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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MmallProductController {
    @Autowired
    MmallProductDao mpdao;

    @RequestMapping(value ="/api/admin/product/list",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> findAll(){
        List<MmallProduct> list=mpdao.findAll();
        if(list!=null&&list.size()>0){
            return JSONResult(0, "查找成功", list);
        }else{
            return JSONResult(1, "暂无商品信息", null);
        }
    }
    @RequestMapping(value ="/api/admin/product",method = RequestMethod.DELETE)
    @ResponseBody
    private Map<String,Object> delete(HttpServletRequest req, HttpServletResponse res) {
        System.out.println(req.getParameter("id"));
        int id=Integer.parseInt(req.getParameter("id").trim());
        System.out.println(id);
        MmallProduct mmallProduct=new MmallProduct();
        mmallProduct.setId(id);
        int rows=mpdao.delete(mmallProduct);
        if(rows>0){
            return JSONResult(0, "删除成功", rows);
        }else{
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
