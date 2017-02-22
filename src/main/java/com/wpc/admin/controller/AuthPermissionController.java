package com.wpc.admin.controller;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.admin.dto.PermissionDto;
import com.wpc.admin.entity.AuthPermission;
import com.wpc.admin.service.AuthPermissionService;
import com.wpc.admin.service.AuthRolePermissionService;
import com.wpc.common.AjaxResult;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/permission")
public class AuthPermissionController {
	
	@Resource(name=AuthPermissionService.BEAN_ID)
	private AuthPermissionService authPermissionService;
	@Resource(name=AuthRolePermissionService.BEAN_ID)
	private AuthRolePermissionService rolePermissionService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String authPermission(ModelMap model) {
		return "admin/authpermission/auth_permission";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthPermission authPermission) {
		AjaxResult ajaxResult = new AjaxResult();
		if(authPermission.getId()!=null && authPermission.getId()!=0){
			authPermissionService.update(authPermission);
		}else{
			authPermissionService.save(authPermission);
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
		authPermissionService.delete(id);
		return ajaxResult;
	}
	
	/**
	 * 根据角色获取权限情况
	 */
	@RequestMapping(value="/getPermissions", method=RequestMethod.POST)
	@ResponseBody
	public List<PermissionDto> getPermissions(Integer rid){
		return authPermissionService.getAllPermissionsByRole(rid);
	}
	
	/**
	 * 根据角色获取权限情况
	 */
	@RequestMapping(value="/saveRolePermissions", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveRolePermissions(int roleId, @RequestParam("pers[]")int[] perIds){
		rolePermissionService.saveRolePermissions(roleId, perIds);
		return AjaxResult.success();
	}	

}
