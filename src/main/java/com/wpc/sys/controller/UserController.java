package com.wpc.sys.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wpc.common.annotation.SysLogAnn;
import com.wpc.common.datatables.DataTablesResponse;
import com.wpc.common.enums.OperType;
import com.wpc.common.msg.AjaxResult;
import com.wpc.sys.model.User;
import com.wpc.sys.service.UserService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.common.datatables.DataTablesRequest;


/**
 * 
 * author wpc
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	PasswordService passwordService;
	
	/**
	 * 页面跳转
	 */
	@RequiresPermissions("user")
	@RequestMapping
	public String user(ModelMap model) {
		return "sys/user";
	}
	
	/**
	 *
	 * 分页查询列表
	 */
	@SysLogAnn(operType = OperType.SYSTEM)
	@RequestMapping(value="/searchPage", method=RequestMethod.POST)
	@ResponseBody
	public DataTablesResponse<User> searchPage(ModelMap model, HttpServletRequest request, @RequestBody DataTablesRequest query) {
		return userService.searchPage(query);
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, User user) {
		if(user.getId()!=null && user.getId()!=0){
			user.setPassword(passwordService.encryptPassword("123456"));
			userService.update(user);
		}else{
			user.setPassword(passwordService.encryptPassword(user.getPassword()));
			userService.save(user);
		}
		return AjaxResult.success();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(ModelMap model, Long id) {
		AjaxResult responseJsonModel = new AjaxResult();
		userService.delete(id);
		return responseJsonModel;
	}
	
	/**
	 * 通过人员名称匹配人员
	 */
	@RequestMapping(value="/likeName", method=RequestMethod.POST)
	@ResponseBody
	public List<User> likeName(ModelMap model, String name) {
		User query = new User();
		query.setLoginName(name+"%");
		return userService.query(query);
	}

	/**
	 * 功能描述: 通过人员id获取人员信息
	 * @Author: wangpengcheng
	 * @Date: 2017-06-20 22:10:32
	 */
	@RequestMapping(value="/findById", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult findById(ModelMap model, Long id) {
		AjaxResult responseJsonModel = new AjaxResult();
		responseJsonModel.setResult(userService.findById(id));
		return responseJsonModel;
	}

}
