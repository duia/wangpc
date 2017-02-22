/**
 * 文件名：JsonTest.java
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
package com.wpc.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：JsonTest
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年2月23日 下午4:04:08
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class JsonTest {

  public static void main(String[] args) throws Exception {
    String json = "{\"1\":[{\"ldapName\":\"A1\",\"sysCode\":\"15010000000\"},{\"ldapName\":\"A2\",\"sysCode\":\"15020000000\"}],\"2\":[{\"ldapName\":\"B1\",\"sysCode\":\"15010100000\"},{\"ldapName\":\"B2\",\"sysCode\":\"15010200000\"}],\"3\":[{\"ldapName\":\"C\",\"sysCode\":\"15010101000\"}],\"4\":[],\"5\":[]}";
    ObjectMapper objectMapper=new ObjectMapper();
    Map<String, Map<String, Object>> maps=objectMapper.readValue(json, Map.class);
    Set<String> key = maps.keySet();
    Iterator<String> iter = key.iterator();
    List<Object> l = null;
    Map<String, String> m = null;
    while (iter.hasNext()) {
        String field = iter.next();
        System.out.println(field + ":" + maps.get(field));
        l = (List<Object>) maps.get(field);
        System.out.println(l.get(0));
        m = (Map<String, String>) l.get(0);
        System.out.println(m.get("ldapName"));
//        Set<String> k = maps.keySet();
//        Iterator<String> i = key.iterator();
//        while (i.hasNext()) {
//            String f = i.next();
//            System.out.println(f + ":" + m.get(f));
//        }
    }
  }

}
