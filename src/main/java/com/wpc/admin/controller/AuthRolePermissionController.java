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

import com.wpc.admin.entity.AuthRolePermission;
import com.wpc.admin.service.AuthRolePermissionService;
import com.wpc.common.AjaxResult;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/role_permission")
public class AuthRolePermissionController {
	
	@Resource(name=AuthRolePermissionService.BEAN_ID)
	private AuthRolePermissionService authRolePermissionService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String authRolePermission(ModelMap model) {
		return "admin/auth/auth_role_permission";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthRolePermission authRolePermission) {
		AjaxResult ajaxResult = new AjaxResult();
		if(authRolePermission.getId()!=null && authRolePermission.getId()!=0){
			authRolePermissionService.update(authRolePermission);
		}else{
			authRolePermissionService.save(authRolePermission);
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
		authRolePermissionService.delete(id);
		return ajaxResult;
	}
	
	

}
