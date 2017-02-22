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

import com.wpc.admin.entity.AuthFilePermission;
import com.wpc.admin.service.AuthFilePermissionService;
import com.wpc.common.AjaxResult;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/authfilepermission")
public class AuthFilePermissionController {
	
	@Resource(name=AuthFilePermissionService.BEAN_ID)
	private AuthFilePermissionService authFilePermissionService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String authFilePermission(ModelMap model) {
		return "admin/authfilepermission/auth_file_permission";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthFilePermission authFilePermission) {
		AjaxResult ajaxResult = new AjaxResult();
		if(authFilePermission.getId()!=null && authFilePermission.getId()!=0){
			authFilePermissionService.update(authFilePermission);
		}else{
			authFilePermissionService.save(authFilePermission);
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
		authFilePermissionService.delete(id);
		return ajaxResult;
	}
	
	

}
