package com.wpc.sys.controller;

import com.wpc.common.msg.AjaxResult;
import com.wpc.sys.model.MenuPermission;
import com.wpc.sys.service.MenuPermissionService;
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
@RequestMapping("/authmenupermission")
public class MenuPermissionController {
	
	@Autowired
	private MenuPermissionService authMenuPermissionService;
	
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
	public AjaxResult addOrUpdate(ModelMap model, MenuPermission authMenuPermission) {
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
	public AjaxResult delete(ModelMap model, Long id) {
		AjaxResult ajaxResult = new AjaxResult();
		authMenuPermissionService.delete(id);
		return ajaxResult;
	}
	
	

}
