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

import com.wpc.admin.entity.AuthUserRole;
import com.wpc.admin.service.AuthUserRoleService;
import com.wpc.common.AjaxResult;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/user_role")
public class AuthUserRoleController {
	
	@Resource(name=AuthUserRoleService.BEAN_ID)
	private AuthUserRoleService authUserRoleService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String authUserRole(ModelMap model) {
		return "admin/auth/auth_user_role";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthUserRole authUserRole) {
		AjaxResult ajaxResult = new AjaxResult();
		if(authUserRole.getId()!=null && authUserRole.getId()!=0){
			authUserRoleService.update(authUserRole);
		}else{
			authUserRoleService.save(authUserRole);
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
		authUserRoleService.delete(id);
		return ajaxResult;
	}
	
	

}
