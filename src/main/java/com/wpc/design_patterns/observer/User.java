/**
 * 文件名：User.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年4月25日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.design_patterns.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：User
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年4月25日 下午5:12:21
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class User implements Observer {
  
  private String name;    
  private Set<Product> focusPdts;

  /**  
   * 通知方法  
   */    
  public void notify(String msg){    
      System.out.println(msg);    
  }    
      
  public User(){    
      focusPdts = new HashSet<Product>();    
  }    
      
  public String getName() {    
      return name;    
  }    
  public void setName(String name) {    
      this.name = name;    
  }    
  public Set<Product> getFocusPdts() {    
      return focusPdts;    
  }    
  public void setFocusPdts(Set<Product> focusPdts) {    
      this.focusPdts = focusPdts;    
  }

}
