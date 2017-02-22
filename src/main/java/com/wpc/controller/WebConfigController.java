/**
 * 文件名：WebConfigController.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年2月19日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.model.LdapObject;
import com.wpc.model.Node;
import com.wpc.util.ConfigUtils;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：WebConfigController
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年2月19日 下午2:15:23
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
@Controller
@RequestMapping("/web")
public class WebConfigController {
  
  String a = "E:/txt/global.ldif";
  String b = "E:/txt/branch.ldif";
  String c = "E:/txt/slapd.conf";
  private Map<String, Node> map = new HashMap<String, Node>();
  
  @RequestMapping(value="/", method={RequestMethod.GET})
  public String jump(){
    return "web/webConfig";
  }
  
  @RequestMapping(value="/config_{fileName}", produces = "application/json; charset=utf-8")
  public @ResponseBody String getConfig(@PathVariable("fileName")String fileName){
    return "哈哈哈哈";
  }
  
  @RequestMapping(value="/a")
  public @ResponseBody String a(String tree, String json, String childNode){
    try {
      System.out.println(tree);
      System.out.println(json);
      System.out.println(childNode);
      makeLdapList(tree);
      
      List<String> s = ConfigUtils.makeGlobalConfig();
      
//      List<LdapObject> ldapList = new ArrayList<LdapObject>();
//      LdapObject lo = null;
//      Set<String> set = map.keySet();
//      for (String s : set) {
//        lo = new LdapObject();
//        lo.setOu(makeLdapOu(map.get(s),""));
//        lo.setSysCode(map.get(s).getSysCode());
//        ldapList.add(lo);
//      }
//      List<String> s = ConfigUtils.makeBranchConfig(ldapList);
      
//      ObjectMapper objectMapper=new ObjectMapper();
//      Map<String, String> mm = objectMapper.readValue(json, Map.class);
//      Node[] nodes=objectMapper.readValue(childNode, Node[].class);
//      List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
//      Map<String, String> m = null;
//      for (Node node : nodes) {
//        m = new HashMap<String, String>();
//        m.put("IP", node.getIp());
//        m.put("PW", node.getPassword());
//        m.put("OU", makeLdapOu(node,""));
//        lists.add(m);
//      }
//      String ip = mm.get("ip");
//      List<String> s = ConfigUtils.makeSlapdConfig(ip.substring(ip.lastIndexOf(".")+1), mm.get("password"), lists);
      
      for (String string : s) {
        System.out.println(string+System.getProperty("line.separator"));
      }
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    

    return "";
  }
  
  /**
   * 
   * <b>Method makeLdapList</b>
   * <dd>方法作用：处理前台传来的ldap服务器层级结构部门树
   * @param tree
   * @return
   * @throws Exception
   * @since Met 1.0
   */
  private void makeLdapList(String tree) throws Exception{
    try {
      ObjectMapper objectMapper=new ObjectMapper();
      Node[] nodes=objectMapper.readValue(tree, Node[].class);
      for (Node node : nodes) {
//        System.out.println(node.getLdapName());
        map.put(node.getId(), node);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 
   * <b>Method makeLdapObject</b>
   * <dd>方法作用：通过父id返回最终ou
   * @since Met 1.0
   */
  private String makeLdapOu(Node node, String ou){
    if(!StringUtils.isEmpty(node.getpId())){
      Node n = map.get(node.getpId());
      ou = makeLdapOu(n, ou);
    }
    ou = "ou="+node.getSysCode()+","+ou;
    return ou;
  }
  
}
