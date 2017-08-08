package com.wpc.sys.controller;

import javax.servlet.http.HttpServletRequest;

import com.wpc.common.datatables.DataTablesResponse;
import com.wpc.common.msg.AjaxResult;
import com.wpc.sys.model.Role;
import com.wpc.sys.model.User;
import com.wpc.sys.model.UserRole;
import com.wpc.sys.service.RoleService;
import com.wpc.sys.service.UserRoleService;
import com.wpc.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.common.datatables.DataTablesRequest;

import java.util.List;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String role(ModelMap model) {
		return "admin/sys/sys_role";
	}
	
	/**
	 *
	 * 分页查询列表
	 */
	@RequestMapping(value="/searchPage", method=RequestMethod.POST)
	@ResponseBody
	public DataTablesResponse<Role> searchPage(ModelMap model, HttpServletRequest request, @RequestBody DataTablesRequest query) {
		return roleService.searchPage(query);
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, Role role) {
		AjaxResult ajaxResult = new AjaxResult();
		if(role.getId()!=null && role.getId()!=0){
			roleService.update(role);
		}else{
			roleService.save(role);
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
		roleService.delete(id);
		return ajaxResult;
	}
	
	/**
	 * 获取所有角色
	 */
	@RequestMapping(value="/allRoles", method=RequestMethod.POST)
	@ResponseBody
	public List<Role> getAllRoles(ModelMap model) {
		return roleService.queryAll();
	}
	
	/**
	 * 通过角色id获取所有该角色下的人员
	 */
	@RequestMapping(value="/addUserRole", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addUserRole(ModelMap model, UserRole aur) {
		userRoleService.save(aur);
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
