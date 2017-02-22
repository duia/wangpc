package com.wpc.admin.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.admin.entity.User;
import com.wpc.admin.service.UserService;
import com.wpc.common.AjaxResult;
import com.wpc.common.datatables.DataTablesRequest;
import com.wpc.common.datatables.DataTablesResponse;


/**
 * 
 * author wpc
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource(name=UserService.BEAN_ID)
	private UserService userService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String user(ModelMap model) {
		return "admin/auth/auth_user";
	}
	
	/**
	 *
	 * 分页查询列表
	 */
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
			userService.update(user);
		}else{
			userService.save(user);
		}
		return AjaxResult.success();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(ModelMap model, Integer id) {
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
		query.setUsername(name+"%");
		return userService.query(query);
	}

}
