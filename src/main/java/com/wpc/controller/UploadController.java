package com.wpc.controller;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.common.utils.UploadUtils;
import com.wpc.common.utils.entity.FileMeta;
import com.wpc.common.utils.entity.Parameter;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping
    public String upload(ModelMap model) {
        return "my/upload";
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public List<FileMeta> fileUpload2(HttpServletRequest request) throws IOException {
    	long  startTime=System.currentTimeMillis();
    	String path=Parameter.uploadPath;
    	List<FileMeta> list = UploadUtils.upload(request, path);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return list; 
    }
    
    /*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping("springUpload")
    @ResponseBody
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
    	String callback = request.getParameter("CKEditorFuncNum");
    	long  startTime=System.currentTimeMillis();
    	String path=Parameter.uploadPath;
    	List<FileMeta> list = UploadUtils.upload(request, path);
        long  endTime=System.currentTimeMillis();
        String imageurl = "";
        if(list.size()>0) imageurl = list.get(0).getSrc();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        String ss = "";
        ss += "<script type=\"text/javascript\">";
        ss += "window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageurl + "','')";
        ss += "</script>"; 
        return ss; 
    }

    @RequestMapping(value="fileUpload2", method= RequestMethod.POST, headers = "content-type=multipart/*")
    @ResponseBody
    public List<FileMeta> uploadFile2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long  startTime=System.currentTimeMillis();
        String path=Parameter.uploadPath;
        List<FileMeta> list = UploadUtils.uploadChunk(request, path);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return list;
    }

}
