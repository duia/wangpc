/**
 * 文件名：ThreadTest.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年6月2日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.test.lang;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：ThreadTest
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年6月2日 下午5:23:11
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class ThreadTest {

  public static void main(String[] args) {
    
    System.out.println("主线程开始运行...");
    
//    Thread da1 = new DoAnything("张三");
//    Thread da2 = new DoAnything("李四");
//    
//    da1.start();
//    da2.start();
//    da2.start();
    
    DoSomething ds1 = new DoSomething("张三");
//    DoSomething ds2 = new DoSomething("李四");
    
    Thread t1 = new Thread(ds1,"A");
    Thread t2 = new Thread(ds1,"B");
    Thread t3 = new Thread(ds1,"C");
//    Thread t2 = new Thread(ds2);
    
    t1.start();
//    try {
//      t1.join();
//    } catch (InterruptedException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    t2.start();
//    t2.yield();
//    t2.setPriority(Thread.MAX_PRIORITY);
    t3.start();
    
    
    System.out.println("主线程运行结束！！！");
    
  }
  
}

class DoAnything extends Thread {

  private int count = 500;
  private String name;
  
  public DoAnything(String name) {
    super();
    this.name = name;
  }

  @Override
  public void run() {
    for (int i = 0; i < 50; i++) {
      System.out.println(Thread.currentThread().getName()+"--"+this.name+"--"+i+"--"+this.count--);
      try {
        Thread.sleep((int)Math.random()*10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  
  
}

class DoSomething implements Runnable {

  private int count=500;
  private String name;
  
  public DoSomething(String name) {
    super();
    this.name = name;
  }

  @Override
  public void run() {
    synchronized (this) {
      for (int i = 0; i < 50; i++) {
        System.out.println(Thread.currentThread().getName()+"--"+this.name+"--"+i+"--"+this.count--);
        try {
          Thread.sleep((int)Math.random()*10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      
    }
  }
  
}
