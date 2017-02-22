package com.wpc.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.wpc.util.entity.FileMeta;
import com.wpc.util.entity.Parameter;

/**
 * 文件上传工具类
 * @author wpc
 *
 */
public class UploadUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(UploadUtils.class);
	
	/**
	 * 文件上传，支持多文件上传
	 * @return 返回文件信息
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static List<FileMeta> upload(HttpServletRequest request, String path) throws IllegalStateException, IOException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssS", Locale.US);
		List<FileMeta> list = new ArrayList<>();
		//上传路径
        File newFile = new File(path);
        if(!newFile.exists()){
        	newFile.mkdirs();
        }
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
        	//将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iter=multiRequest.getFileNames();
            //遍历所有文件
            while(iter.hasNext()){
            	MultipartFile file=multiRequest.getFile(iter.next());
            	if(file!=null && !file.isEmpty()){
            		//重命名上传后的文件名  
                    String fileName = new String(formatter.format(new Date()).getBytes("utf-8")) + file.getOriginalFilename(); 
                    //上传
                    file.transferTo(new File(path, fileName));
                    
                    FileMeta fileMeta = new FileMeta();
                    fileMeta.setSrc(Parameter.uploadServerPath+fileName);
                    fileMeta.setPath(path+fileName);
                    fileMeta.setFileName(file.getOriginalFilename());
                    fileMeta.setFileSize(file.getSize() / 1024 + " Kb");
                    fileMeta.setFileType(file.getContentType());
                    list.add(fileMeta);
            	}
            }
        }
		return list;
	}

}
