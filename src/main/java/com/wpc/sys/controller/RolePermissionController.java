package com.wpc.sys.controller;

import com.wpc.common.msg.AjaxResult;
import com.wpc.sys.model.RolePermission;
import com.wpc.sys.service.RolePermissionService;
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
@RequestMapping("/sys/role_permission")
public class RolePermissionController {
	
	@Autowired
	private RolePermissionService rolePermissionService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String rolePermission(ModelMap model) {
		return "sys/role_permission";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, RolePermission rolePermission) {
		AjaxResult ajaxResult = new AjaxResult();
		if(rolePermission.getId()!=null && rolePermission.getId()!=0){
			rolePermissionService.update(rolePermission);
		}else{
			rolePermissionService.save(rolePermission);
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
		rolePermissionService.delete(id);
		return ajaxResult;
	}
	
	

}
