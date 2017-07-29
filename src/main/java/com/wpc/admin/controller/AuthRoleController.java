package com.wpc.admin.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wpc.common.msg.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.admin.entity.AuthRole;
import com.wpc.admin.entity.AuthUserRole;
import com.wpc.admin.entity.User;
import com.wpc.admin.service.AuthRoleService;
import com.wpc.admin.service.AuthUserRoleService;
import com.wpc.admin.service.UserService;
import com.wpc.common.datatables.DataTablesRequest;
import com.wpc.common.datatables.DataTablesResponse;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/role")
public class AuthRoleController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthRoleService authRoleService;
	@Autowired
	private AuthUserRoleService authUserRoleService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String authRole(ModelMap model) {
		return "admin/auth/auth_role";
	}
	
	/**
	 *
	 * 分页查询列表
	 */
	@RequestMapping(value="/searchPage", method=RequestMethod.POST)
	@ResponseBody
	public DataTablesResponse<AuthRole> searchPage(ModelMap model, HttpServletRequest request, @RequestBody DataTablesRequest query) {
		return authRoleService.searchPage(query);
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthRole authRole) {
		AjaxResult ajaxResult = new AjaxResult();
		if(authRole.getId()!=null && authRole.getId()!=0){
			authRoleService.update(authRole);
		}else{
			authRoleService.save(authRole);
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
		authRoleService.delete(id);
		return ajaxResult;
	}
	
	/**
	 * 获取所有角色
	 */
	@RequestMapping(value="/allRoles", method=RequestMethod.POST)
	@ResponseBody
	public List<AuthRole> getAllRoles(ModelMap model) {
		return authRoleService.queryAll();
	}
	
	/**
	 * 通过角色id获取所有该角色下的人员
	 */
	@RequestMapping(value="/addUserRole", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addUserRole(ModelMap model, AuthUserRole aur) {
		authUserRoleService.save(aur);
		return AjaxResult.success();
	}
	
	/**
	 * 通过角色id获取所有该角色下的人员
	 */
	@RequestMapping(value="/userByRole", method=RequestMethod.POST)
	@ResponseBody
	public List<User> userByRole(ModelMap model, Long roleId) {
		return userService.queryUserByRole(roleId);
	}
	

}
