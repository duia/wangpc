package com.wpc.sys.controller;

import com.wpc.common.msg.AjaxResult;
import com.wpc.sys.model.File;
import com.wpc.sys.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  控制层
 * author wpc
 */
@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	/**
	 * 页面跳转
	 */
	@RequestMapping
	public String file(ModelMap model) {
		return "admin/sys/sys_file";
	}
	
	/**
	 * 保存或更新
	 */
	@RequestMapping(value="/addOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addOrUpdate(ModelMap model, File file) {
		AjaxResult ajaxResult = new AjaxResult();
		if(file.getId()!=null && file.getId()!=0){
			fileService.update(file);
		}else{
			fileService.save(file);
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
		fileService.delete(id);
		return ajaxResult;
	}
	
	

}
