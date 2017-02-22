/**
 * 文件名：H2Test.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年3月8日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：H2Test
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年3月8日 上午11:48:39
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class H2Test {

  //数据库连接URL，通过使用TCP/IP的服务器模式（远程连接），当前连接的是内存里面的gacl数据库
  private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
  // 连接数据库时使用的用户名
  private static final String USER = "sa";
  // 连接数据库时使用的密码
  private static final String PASSWORD = "";
  // 连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
  private static final String DRIVER_CLASS = "org.h2.Driver";

  public static void main(String[] args) throws Exception {
    // 加载H2数据库驱动
    Class.forName(DRIVER_CLASS);
    // 根据连接URL，用户名，密码获取数据库连接
    Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    Statement stmt = conn.createStatement();
    // 如果存在USER_INFO表就先删除USER_INFO表
//    stmt.execute("DROP TABLE IF EXISTS USER_INFO");
    // 创建USER_INFO表
//    stmt.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
    // 新增
//    stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','大日如来','男')");
//    stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','青龙','男')");
//    stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','白虎','男')");
//    stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','朱雀','女')");
//    stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','玄武','男')");
//    stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','苍狼','男')");
    // 删除
//    stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='大日如来'");
    // 修改
//    stmt.executeUpdate("UPDATE USER_INFO SET name='孤傲苍狼' WHERE name='苍狼'");
    // 查询
    ResultSet rs = stmt.executeQuery("select count(*) count from public.TEST");
    // 遍历结果集
    while (rs.next()) {
      System.out.println(rs.getString("count"));
    }
    // 释放资源
    stmt.close();
    // 关闭连接
    conn.close();
  }

  
}
