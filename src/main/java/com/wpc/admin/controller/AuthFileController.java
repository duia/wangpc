package com.wpc.admin.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wpc.admin.entity.AuthFile;
import com.wpc.admin.service.AuthFileService;
import com.wpc.common.AjaxResult;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/authfile")
public class AuthFileController {
	
	@Resource(name=AuthFileService.BEAN_ID)
	private AuthFileService authFileService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String authFile(ModelMap model) {
		return "admin/authfile/auth_file";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthFile authFile) {
		AjaxResult ajaxResult = new AjaxResult();
		if(authFile.getId()!=null && authFile.getId()!=0){
			authFileService.update(authFile);
		}else{
			authFileService.save(authFile);
		}
		return ajaxResult;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(ModelMap model, Integer id) {
		AjaxResult ajaxResult = new AjaxResult();
		authFileService.delete(id);
		return ajaxResult;
	}
	
	

}
