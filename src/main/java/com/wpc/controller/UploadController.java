package com.wpc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.wpc.util.UploadUtils;
import com.wpc.util.entity.FileMeta;
import com.wpc.util.entity.Parameter;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("fileUpload")
    @ResponseBody
    public List<FileMeta> fileUpload2(HttpServletRequest request) throws IOException {
    	long  startTime=System.currentTimeMillis();
    	String path=Parameter.uploadPath;
    	List<FileMeta> list = UploadUtils.upload(request, path);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
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
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        String ss = "";
        ss += "<script type=\"text/javascript\">";
        ss += "window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageurl + "','')";
        ss += "</script>"; 
        return ss; 
    }
    
}
