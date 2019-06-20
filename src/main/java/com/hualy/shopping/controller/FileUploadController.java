package com.hualy.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Controller
public class FileUploadController {
    /* 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @RequestMapping("/file/upload")
    @ResponseBody
    public Map<String, Object> fileUpload(HttpServletRequest req) throws IOException {


        //用来检测程序运行时间

        long startTime = System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        String resultName = "";
        if (multipartResolver.isMultipart(req)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    String fileName = file.getOriginalFilename();
                    resultName = uuid + fileName.substring(fileName.lastIndexOf("."));
                    String path = (req.getSession().getServletContext().getRealPath("/") + "/upload/" + resultName);
                    //上传
                    file.transferTo(new File(path));
                }
            }

        }
        return JSONResult(0, "success", resultName);
    }

    private Map<String, Object> JSONResult(int code, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);
        return result;
    }
}
