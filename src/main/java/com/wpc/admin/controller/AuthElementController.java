package com.wpc.admin.controller;
import java.util.List;

import javax.annotation.Resource;

import com.wpc.common.msg.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.admin.entity.AuthElement;
import com.wpc.admin.service.AuthElementService;


/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/element")
public class AuthElementController {
	
	@Autowired
	private AuthElementService authElementService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String authElement(ModelMap model) {
		return "admin/auth/auth_element";
	}
	
	@RequestMapping(value="/queryElementByMenu", method=RequestMethod.POST)
	@ResponseBody
	public List<AuthElement> queryElementByMenu(int menuId){
		return authElementService.queryElementByMenuId(menuId);
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, AuthElement authElement) {
		AjaxResult ajaxResult = new AjaxResult();
		authElementService.saveOrUpdate(authElement);
		return ajaxResult;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(ModelMap model, Long id) {
		authElementService.delete(id);
		return AjaxResult.success();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/findById", method=RequestMethod.POST)
	@ResponseBody
	public AuthElement findById(Long id){
		return authElementService.findById(id);
	}

}
