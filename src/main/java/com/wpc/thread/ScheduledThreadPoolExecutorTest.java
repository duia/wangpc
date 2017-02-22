/**
 * 文件名：ThreadPoolExecutorTest.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年4月26日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：ThreadPoolExecutorTest
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年4月26日 下午4:57:41
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class ScheduledThreadPoolExecutorTest {

  public static void main(String[] args) {
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    
    for (int i = 0; i < 10; i++) {
      final int index = i;
      scheduledThreadPool.scheduleAtFixedRate(new ThreadTest("a"+1), 1, 5, TimeUnit.SECONDS);
    }
    
  }
  
}

class ThreadTest implements Runnable {

  private String name;
  
  public ThreadTest(String name){
    this.name = name;
    Thread.currentThread().setName(name);
  }
  
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName()+"...");
  }
  
}
