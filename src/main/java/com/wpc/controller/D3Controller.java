/**
 * 文件名：QuneeController.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年2月29日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：QuneeController
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年2月29日 下午4:50:38
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
@Controller
@RequestMapping("/d3")
public class D3Controller {

  @RequestMapping(value="/demo/{code}", method={RequestMethod.GET})
  public String a(@PathVariable("code")String code){
    return "/d3/demo"+code;
  }
  
  @RequestMapping(value="/{name}", method={RequestMethod.GET})
  public String b(@PathVariable("name")String name){
    return "/d3/"+name;
  }
  
}
