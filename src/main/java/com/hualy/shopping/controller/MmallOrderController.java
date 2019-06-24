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
        Map<String, Object> list =dao.findAll();
        return JSONResult(0, "success", list);
    }


    @RequestMapping(value = "/api/mmallorder/update", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> update(HttpServletRequest req, HttpServletResponse res) {
        MmallOrder mmallOrder=new MmallOrder();
        int id=Integer.parseInt(req.getParameter("id"));
        int userId=Integer.parseInt(req.getParameter("user_id"));
        int orderId=Integer.parseInt(req.getParameter("order_id"));
        int shippingId=Integer.parseInt(req.getParameter("shipping_id"));
        int paymentType=Integer.parseInt(req.getParameter("payment_type"));
        int postage=Integer.parseInt(req.getParameter("postage"));
        int status=Integer.parseInt(req.getParameter("status"));
        String paymentTime=req.getParameter("payment_time");
        String sendTime=req.getParameter("send_time");
        String endTime=req.getParameter("end_time");
        String closeTime=req.getParameter("close_time");
        String createTime=req.getParameter("create_time");
        return JSONResult(0, "用户已登录过", null);
    }


    private Map<String, Object> JSONResult(int code, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);
        return result;
    }
}
