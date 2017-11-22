package com.wpc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.common.utils.file.DownLoadUtil;
import com.wpc.common.utils.io.ZipUtils;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @RequestMapping("/test")
    @ResponseBody
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	long  startTime=System.currentTimeMillis();
    	
    	String path = "E:/download/libs_1.zip";//打包生成的文件 即要下载的文件
    	ZipUtils.zipFile(Arrays.asList(new File("E:/excel").listFiles()), path);
    	//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");  
        //2.设置文件头：最后一个参数是设置下载文件名
        response.setHeader("Content-Disposition", DownLoadUtil.transFileName("attachment;fileName="+"libs_1.zip", request));  
        ServletOutputStream out;  
        //通过文件路径获得File对象
        File file = new File(path);  
  
        try {  
            FileInputStream inputStream = new FileInputStream(file);  
  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
  
            int b = 0;  
            byte[] buffer = new byte[1024 * 4];  
            while ((b = inputStream.read(buffer)) != -1){  
                //4.写到输出流(out)中  
                out.write(buffer,0,b);  
            }  
            inputStream.close();  
            out.close();  
            out.flush();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
    	
        long  endTime=System.currentTimeMillis();
        System.out.println("运行时间："+String.valueOf(endTime-startTime)+"ms");
    }
    
}
