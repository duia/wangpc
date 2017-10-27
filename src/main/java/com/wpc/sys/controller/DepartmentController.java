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

import com.wpc.sys.model.Department;
import com.wpc.sys.service.DepartmentService;

/**
 * @version 1.0
 * @author 
 */
@Controller
@RequestMapping("/sys/department")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String index(ModelMap model) {
		return "sys/department";
	}

	/**
	 *
	 * 分页查询列表
	 */
	@RequestMapping(value="/searchPage", method=RequestMethod.POST)
	@ResponseBody
	public DataTablesResponse<Department> searchPage(ModelMap model, HttpServletRequest request, @RequestBody DataTablesRequest query) {
		return departmentService.searchPage(query);
	}

	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, Department department) {
		AjaxResult ajaxResult = new AjaxResult();
		if(department.getId()!=null && department.getId()!=0){
			departmentService.update(department);
		}else{
			departmentService.save(department);
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
		departmentService.delete(id);
		return ajaxResult;
	}

}
