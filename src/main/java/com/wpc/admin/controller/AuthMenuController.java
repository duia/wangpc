package com.wpc.admin.controller;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.admin.entity.AuthMenu;
import com.wpc.admin.service.AuthMenuService;
import com.wpc.common.AjaxResult;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/menu")
public class AuthMenuController {
	
	@Resource(name=AuthMenuService.BEAN_ID)
	private AuthMenuService authMenuService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String menu(ModelMap model) {
		return "admin/auth/auth_menu";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthMenu menu) {
		menu.setUpdateTime(new Date());
		if(menu.getId()!=null && menu.getId()!=0){
			authMenuService.update(menu);
		}else{
			authMenuService.save(menu);
		}
		return AjaxResult.success();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(ModelMap model, Integer id) {
		authMenuService.delete(id);
		return AjaxResult.success();
	}
	
	/**
	 * 获取所有菜单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getAllMenus", method=RequestMethod.POST)
	@ResponseBody
	public List<AuthMenu> getAllMenus(ModelMap model) {
		return authMenuService.queryAll();
	}
	
	/**
	 * 主页面获取可用菜单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getLeftMenus")
	@ResponseBody
	public List<AuthMenu> getLeftMenus(ModelMap model) {
		return authMenuService.getLeftMenu();
	}

}
