package com.wpc.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.wpc.util.UploadUtils;
import com.wpc.util.entity.FileMeta;
import com.wpc.util.entity.Parameter;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

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
