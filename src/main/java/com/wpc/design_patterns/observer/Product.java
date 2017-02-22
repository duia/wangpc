/**
 * 文件名：Product.java
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：Product
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年4月25日 下午5:10:38
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class Product {

  private String name;    
  private double price;    
  private List<Observer> focusUsers;//观察者集合    
      
  /**  
   * 价格折扣  
   * @param off  
   */    
  public synchronized void payOff(double off){    
      this.price = getPrice() * (1 - off);            
      StringBuffer msg = null;    
          
      if(focusUsers != null && !focusUsers.isEmpty()){    
          Iterator<Observer> it = focusUsers.iterator();    
          while(it.hasNext()){    
              Observer user = (Observer)it.next();    
                  
              String msgPart = ", "+ this.getName() +"的价格 "+ this.getPrice() +", 价格折扣 "+ off * 100 +"%!";    
              msg = new StringBuffer();    
              msg.append("~~~~ 您好 "+ user.getName());    
              msg.append(msgPart);    
                  
              user.notify(msg.toString());//发送提醒    
          }    
      }    
  }    
      
  /**  
   * 添加关注用户  
   * @param user  
   */    
  public void addFocusUsers(User user) {    
      this.getFocusUsers().add(user);    
  }    
  /**  
   * 删除关注用户  
   * @param user  
   */    
  public void delFocusUser(User user) {    
      this.getFocusUsers().remove(user);    
  }    
      
      
  public Product(){    
      focusUsers = new ArrayList<Observer>();    
  }    
      
  public String getName() {    
      return name;    
  }    
  public void setName(String name) {    
      this.name = name;    
  }    
  public double getPrice() {    
      return price;    
  }    
  public void setPrice(double price) {    
      this.price = price;    
  }    
      
      
  public List<Observer> getFocusUsers() {    
      return focusUsers;    
  }    
  public void setFocusUsers(List<Observer> focusUsers) {    
      this.focusUsers = focusUsers;    
  } 
  
}
