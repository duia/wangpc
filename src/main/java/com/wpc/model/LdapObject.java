/**
 * 文件名：LdapObject.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年2月22日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：LdapObject
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年2月22日 下午2:52:51
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class LdapObject {

  private String ou;
  private String sysCode;
  
  public String getOu() {
    return ou;
  }
  public void setOu(String ou) {
    this.ou = ou;
  }
  public String getSysCode() {
    return sysCode;
  }
  public void setSysCode(String sysCode) {
    this.sysCode = sysCode;
  }
  
}
