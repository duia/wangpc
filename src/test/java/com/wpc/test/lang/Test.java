/**
 * 文件名：Test.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年6月1日
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
 *  <dd> 类名称：Test
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年6月1日 下午1:18:54
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class Test implements Cloneable {

  private String name;
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Test other = (Test) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  public static void main(String[] args) throws Exception {
    
    Test t = new Test();
    t.setName("123");
    Test t2 = (Test) t.clone();
    System.out.println(t.equals(t2));
    Test t3 = new Test();
    t3.setName("321");
    System.out.println(t.getClass() + "  " + Test.class);
    System.out.println(t.hashCode() + "  " + t2.hashCode());
    System.out.println(t3.hashCode());
    System.out.println(t3.equals(t));
    System.out.println(t3.toString());
    
    t3.wait(1000);
    
    System.out.println("123");
    
  }
  
}
