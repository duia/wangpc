/**
 * 文件名：MyAbstract.java
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
package com.wpc.test;

import java.util.HashMap;
import java.util.Map;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：MyAbstract
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年6月2日 上午9:35:03
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
abstract class MyAbstract {

  private String name;
  
  public abstract void run();
  
  public void test(){
    
  }
  
}

interface MyInterface {
  
  public void run();
  
}

public class MyAbsInt extends MyAbstract {

  private long times;
  
  @Override
  public void run() {
    
  }
  
  
  public static void main(String[] args) {
    
//    List list = new ArrayList();
//    list.add("");
//    list.add("");
//    list.add(null);
//    list.add(null);
//    list.add(null);
//    list.add(null);
//    list.add(null);
//    System.out.println(list.size());
    
    Map<String,String> map = new HashMap<String,String>();
    map.put(null, null);
    map.put(null, null);
    map.put(null, null);
    map.put(null, null);
    map.put(null, null);
    map.put(null, null);
    map.put(null, null);
    map.put(null, null);
    map.put(null, null);
    map.put(null, null);
    map.put(null, null);
    System.out.println(map.size());
    System.out.println(map.get(null));
    
  }
  
  
}


