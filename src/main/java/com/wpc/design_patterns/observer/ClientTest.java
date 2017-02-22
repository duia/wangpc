/**
 * 文件名：ClientTest.java
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

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：ClientTest
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年4月25日 下午5:13:57
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class ClientTest {

  public static void main(String[] args) {    
    //产品    
    Product mobile = new Product();    
    mobile.setName("SAMSUNG手机");    
    mobile.setPrice(2000);    
        
    Product book = new Product();    
    book.setName("JAVA设计模式");    
    book.setPrice(80);    
        
    //用户    
    User user1 = new User();    
    user1.setName("张三");    
    user1.getFocusPdts().add(mobile);//关注某一款三星手机    
    //user1.getFocusPdts().add(book);//关注JAVA设计模式    
        
    User user2 = new User();    
    user2.setName("李四");    
    user2.getFocusPdts().add(mobile);//关注某一款三星手机    
    user2.getFocusPdts().add(book);//关注JAVA设计模式    
        
    //建立商品和订阅者关联    
    mobile.getFocusUsers().add(user1);    
    book.getFocusUsers().add(user1);    
    book.getFocusUsers().add(user2);    
        
    //产品打折,发送站内信提醒    
    mobile.payOff(0.1);    
    book.payOff(0.2);    
}
  
}
