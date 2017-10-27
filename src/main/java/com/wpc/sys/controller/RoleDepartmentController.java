/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.controller;

import javax.servlet.http.HttpServletRequest;

import com.wpc.common.datatables.DataTablesRequest;
import com.wpc.common.datatables.DataTablesResponse;
import com.wpc.common.msg.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpc.sys.model.RoleDepartment;
import com.wpc.sys.service.RoleDepartmentService;

/**
 * @version 1.0
 * @author 
 */
@Controller
@RequestMapping("/sys/roleDepartment")
public class RoleDepartmentController {
    
    @Autowired
    private RoleDepartmentService roleDepartmentService;

	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String index(ModelMap model) {
		return "sys/roleDepartment";
	}

	/**
	 *
	 * 分页查询列表
	 */
	@RequestMapping(value="/searchPage", method=RequestMethod.POST)
	@ResponseBody
	public DataTablesResponse<RoleDepartment> searchPage(ModelMap model, HttpServletRequest request, @RequestBody DataTablesRequest query) {
		return roleDepartmentService.searchPage(query);
	}

	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, RoleDepartment roleDepartment) {
		AjaxResult ajaxResult = new AjaxResult();
		if(roleDepartment.getId()!=null && roleDepartment.getId()!=0){
			roleDepartmentService.update(roleDepartment);
		}else{
			roleDepartmentService.save(roleDepartment);
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
		roleDepartmentService.delete(id);
		return ajaxResult;
	}

}
