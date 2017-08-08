package com.wpc.sys.controller;

import com.wpc.common.msg.AjaxResult;
import com.wpc.sys.model.ElementPermission;
import com.wpc.sys.service.ElementPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/authelementpermission")
public class ElementPermissionController {
	
	@Autowired
	private ElementPermissionService authElementPermissionService;
	
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
	public AjaxResult addOrUpdate(ModelMap model, ElementPermission authElementPermission) {
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
	public AjaxResult delete(ModelMap model, Long id) {
		AjaxResult ajaxResult = new AjaxResult();
		authElementPermissionService.delete(id);
		return ajaxResult;
	}
	
	

}
