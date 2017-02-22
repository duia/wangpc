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

import com.wpc.admin.entity.AuthMenuPermission;
import com.wpc.admin.service.AuthMenuPermissionService;
import com.wpc.common.AjaxResult;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/authmenupermission")
public class AuthMenuPermissionController {
	
	@Resource(name=AuthMenuPermissionService.BEAN_ID)
	private AuthMenuPermissionService authMenuPermissionService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String authMenuPermission(ModelMap model) {
		return "admin/authmenupermission/auth_menu_permission";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthMenuPermission authMenuPermission) {
		AjaxResult ajaxResult = new AjaxResult();
		if(authMenuPermission.getId()!=null && authMenuPermission.getId()!=0){
			authMenuPermissionService.update(authMenuPermission);
		}else{
			authMenuPermissionService.save(authMenuPermission);
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
		authMenuPermissionService.delete(id);
		return ajaxResult;
	}
	
	

}
