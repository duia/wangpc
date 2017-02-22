/**
 * 文件名：Node.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年2月23日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.model;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：Node
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年2月23日 下午12:28:44
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class Node {
  private String id;
  private String ip;
  private String pId;
  private int level;
  private String ldapName;
  private String sysCode;
  private String platCode;
  private String password;
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public int getLevel() {
    return level;
  }
  public void setLevel(int level) {
    this.level = level;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getpId() {
    return pId;
  }
  public void setpId(String pId) {
    this.pId = pId;
  }
  public String getLdapName() {
    return ldapName;
  }
  public void setLdapName(String ldapName) {
    this.ldapName = ldapName;
  }
  public String getSysCode() {
    return sysCode;
  }
  public void setSysCode(String sysCode) {
    this.sysCode = sysCode;
  }
  public String getPlatCode() {
    return platCode;
  }
  public void setPlatCode(String platCode) {
    this.platCode = platCode;
  }
}
