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

import com.wpc.admin.entity.AuthElementPermission;
import com.wpc.admin.service.AuthElementPermissionService;
import com.wpc.common.AjaxResult;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/authelementpermission")
public class AuthElementPermissionController {
	
	@Resource(name=AuthElementPermissionService.BEAN_ID)
	private AuthElementPermissionService authElementPermissionService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String authElementPermission(ModelMap model) {
		return "admin/authelementpermission/auth_element_permission";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthElementPermission authElementPermission) {
		AjaxResult ajaxResult = new AjaxResult();
		if(authElementPermission.getId()!=null && authElementPermission.getId()!=0){
			authElementPermissionService.update(authElementPermission);
		}else{
			authElementPermissionService.save(authElementPermission);
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
		authElementPermissionService.delete(id);
		return ajaxResult;
	}
	
	

}
