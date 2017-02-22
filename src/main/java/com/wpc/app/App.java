/**
 * 文件名：App.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年4月15日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：EntireNetworkMoing
 *  <dd> 类名称：App
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年4月15日 上午10:53:27
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);
  
  private String classPath = App.class.getResource("/").getPath();//.substring(1, classPath.length());
  
  public void ktrRun(){
    logger.info("运行开始...");
    long startTime = System.currentTimeMillis();
    System.out.println("任务调度...");
    long endTime = System.currentTimeMillis();
    logger.info("运行结束！耗时{}毫秒", endTime-startTime);
  }
  
}
