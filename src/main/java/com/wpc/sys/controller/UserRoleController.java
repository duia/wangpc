package com.wpc.sys.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wpc.common.msg.AjaxResult;
import com.wpc.sys.model.UserRole;
import com.wpc.sys.service.UserRoleService;
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


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/sys/user_role")
public class UserRoleController {
	
	@Resource
	private UserRoleService userRoleService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String userRole(ModelMap model) {
		return "sys/user_role";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, UserRole userRole) {
		AjaxResult ajaxResult = new AjaxResult();
		if(userRole.getId()!=null && userRole.getId()!=0){
			userRoleService.update(userRole);
		}else{
			userRoleService.save(userRole);
		}
		return ajaxResult;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(ModelMap model, Long id) {
		AjaxResult ajaxResult = new AjaxResult();
		userRoleService.delete(id);
		return ajaxResult;
	}
	
	

}
