package com.wpc.sys.controller;
import java.util.Date;
import java.util.List;

import com.wpc.common.msg.AjaxResult;
import com.wpc.sys.model.Menu;
import com.wpc.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService authMenuService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String menu(ModelMap model) {
		return "sys/menu";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, Menu menu) {
		menu.setUpdateDate(new Date());
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
	public AjaxResult delete(ModelMap model, Long id) {
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
	public List<Menu> getAllMenus(ModelMap model) {
		return authMenuService.queryAll();
	}
	
	/**
	 * 主页面获取可用菜单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getLeftMenus")
	@ResponseBody
	public List<Menu> getLeftMenus(ModelMap model) {
		return authMenuService.getLeftMenu();
	}

}
