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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class FixedThreadPoolExecutorTest {

  public static void main(String[] args) {
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 10; i++) {
      final int index = i;
      fixedThreadPool.execute(new Runnable() {
        
        @Override
        public void run() {
          System.out.println(index);
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
    }
  }
  
}
