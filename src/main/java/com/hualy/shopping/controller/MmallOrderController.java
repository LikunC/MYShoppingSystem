package com.hualy.shopping.controller;


import com.hualy.shopping.dao.MmallOrderDao;
import com.hualy.shopping.pojo.MmallOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MmallOrderController {


    @Autowired
    MmallOrderDao dao;


    @RequestMapping(value = "/api/mmallorder/findall",method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getMmallOrder() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = dao.findAll();
        if(list!=null&&list.size()>0){
            return JSONResult(0, "success", list);
        }else{
            return JSONResult(1, "fail", null);
        }

    }


    //发货
    @RequestMapping(value = "/api/mmallorder/updataToShipped", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> updataToShipped(HttpServletRequest req, HttpServletResponse res) {
        MmallOrder mmallOrder=new MmallOrder();
        int id=Integer.parseInt(req.getParameter("id"));
        Date date = new Date();
        DateFormat df = DateFormat.getDateTimeInstance();
        //修改发货时间
        mmallOrder.setSend_time(df.format(date));
        mmallOrder.setId(id);
        int row=dao.updataToShipped(mmallOrder);
        if(row>0){
            return JSONResult(0, "发货成功", null);
        }else{
            return JSONResult(1, "发货失败", null);
        }

    }
    //完成订单
    @RequestMapping(value = "/api/mmallorder/updateToComplete", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> updateToComplete(HttpServletRequest req, HttpServletResponse res) {
        MmallOrder mmallOrder=new MmallOrder();
        int id=Integer.parseInt(req.getParameter("id"));
        Date date = new Date();
        DateFormat df = DateFormat.getDateTimeInstance();
        //修改订单完成时间
        mmallOrder.setEnd_time(df.format(date));
        mmallOrder.setId(id);
        int row=dao.updateToComplete(mmallOrder);
        if(row>0){
            return JSONResult(0, "订单完成", null);
        }else{
            return JSONResult(1, "订单完成失败", null);
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
