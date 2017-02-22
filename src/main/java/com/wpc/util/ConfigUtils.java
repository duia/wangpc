/**
 * 文件名：ConfigUtils.java
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
package com.wpc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wpc.model.LdapObject;

/**
 * <dl>
 * Class Description
 * <dd>项目名称：springmvc
 * <dd>类名称：ConfigUtils
 * <dd>类描述：概述类的作用
 * <dd>创建人：王鹏程
 * <dd>创建时间：2016年2月22日 上午11:41:37
 * <dd>修改人：无
 * <dd>修改时间：无
 * <dd>修改备注：无
 * </dl>
 * 
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class ConfigUtils {

  /**
   * 以行为单位读取文件，常用于读面向行的格式化文件
   */
  public static void readFileByLines(String fileName) {
    File file = new File(fileName);
    BufferedReader reader = null;
    try {
      System.out.println("以行为单位读取文件内容，一次读一整行：");
      reader = new BufferedReader(new FileReader(file));
      String tempString = null;
      int line = 1;
      // 一次读入一行，直到读入null为文件结束
      while ((tempString = reader.readLine()) != null) {
        // 显示行号
        System.out.println("line " + line + ": " + tempString);
        line++;
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
  }

  /**
   * A方法追加文件：使用RandomAccessFile
   */
  public static void appendMethodA(String fileName, String content) {
    try {
      // 打开一个随机访问文件流，按读写方式
      RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
      // 文件长度，字节数
      long fileLength = randomFile.length();
      // 将写文件指针移到文件尾。
      randomFile.seek(fileLength);
      randomFile.writeBytes(content);
      randomFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * B方法追加文件：使用FileWriter
   */
  public static void appendMethodB(String fileName, String content) {
    try {
      // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
      FileWriter writer = new FileWriter(fileName, true);
      writer.write(content);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 
   * <dd>方法作用：生成配置文件
   * 
   * @since Met 1.0
   */
  public static List<String> makeGlobalConfig() {
    File file = new File("C:/Users/wpc/Desktop/config/global.ldif");
    BufferedReader reader = null;
    List<String> list = null;
    try {
      // 以行为单位读取文件内容，一次读一整行
      reader = new BufferedReader(new FileReader(file));
      list = new ArrayList<String>();
      String tempString = null;
      while ((tempString = reader.readLine()) != null) {
        // 进行内容修改
        // tempString
        // 修改完后追加到新文件后
        list.add(tempString);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
    return list;
  }
  
  public static List<String> makeBranchConfig(List<LdapObject> ldapList) {
    File file = new File("C:/Users/wpc/Desktop/config/branch.ldif");
    BufferedReader reader = null;
    List<String> tempList = null;
    List<String> list = null;
    try {
      // 以行为单位读取文件内容，一次读一整行
      reader = new BufferedReader(new FileReader(file));
      tempList = new ArrayList<String>();
      list = new ArrayList<String>();
      String tempString = null;
      int line = 1;
      while ((tempString = reader.readLine()) != null) {
        if(line<6){
          list.add(tempString);
        }else{
          tempList.add(tempString);
        }
        line++;
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
    list.addAll(ConfigUtils.returnBranch(ldapList, tempList));
    return list;
  }
  
  /**
   * 
   * <dd>方法作用：返回branch模板
   * @since Met 1.0
   */
  public static List<String> returnBranch(List<LdapObject> ldapList, List<String> tempList){
    List<String> list = new ArrayList<String>();
    for (LdapObject ldapObject : ldapList) {
      for (String string : tempList) {
        // 进行内容修改
        if(string.contains("%s") && string.startsWith("dn")){
          string = String.format(string, ldapObject.getOu());
        }else if(string.contains("%s") && string.startsWith("ou")){
          string = String.format(string, ldapObject.getSysCode());
        }
        list.add(string);
      }
      list.add(System.getProperty("line.separator"));
    }
    return list;
  }
  
  /**
   * 
   * <b>Method makeSlapdConfig</b>
   * <dd>方法作用：生成slapd.conf配置文件
   * @param ip
   * @param pw
   * @param lists
   * @return
   * @since Met 1.0
   */
  public static List<String> makeSlapdConfig(String ip, String pw, List<Map<String, String>> lists) {
    File globalFile = new File("C:/Users/wpc/Desktop/config/slapd_global");
    File branchFile = new File("C:/Users/wpc/Desktop/config/slapd_branch");
    File file = new File("C:/Users/wpc/Desktop/config/slapd.conf");
    BufferedReader globalReader = null;
    BufferedReader branchReader = null;
    BufferedReader reader = null;
    List<String> globalTempList = null;
    List<String> branchTempList = null;
    List<String> globalList = new ArrayList<String>();
    List<String> branchList = new ArrayList<String>();
    List<String> list = new ArrayList<String>();
    try {
      // 以行为单位读取文件内容，一次读一整行
      globalReader = new BufferedReader(new FileReader(globalFile));
      globalTempList = new ArrayList<String>();
      branchReader = new BufferedReader(new FileReader(branchFile));
      branchTempList = new ArrayList<String>();
      String tempString = null;
      while ((tempString = globalReader.readLine()) != null) {
        globalTempList.add(tempString);
      }
      while ((tempString = branchReader.readLine()) != null) {
        branchTempList.add(tempString);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (globalReader != null) {
        try {
          globalReader.close();
        } catch (IOException e1) {
        }
      }
      if (branchReader != null) {
        try {
          branchReader.close();
        } catch (IOException e1) {
        }
      }
    }
    int id = 1;
    for (Map<String, String> map : lists) {
      for (String string : globalTempList) {
        if(string.contains("ID")){
          string = string.replace("ID", "00"+(id++));
        }else if(string.contains("IP")){
          string = string.replace("IP", map.get("IP"));
        }else if(string.contains("PW")){
          string = string.replace("PW", map.get("PW"));
        }
        globalList.add(string);
      }
      for (String string : branchTempList) {
        if(string.contains("ID")){
          string = string.replace("ID", "00"+(id++));
        }else if(string.contains("IP")){
          string = string.replace("IP", map.get("IP"));
        }else if(string.contains("PW")){
          string = string.replace("PW", map.get("PW"));
        }else if(string.contains("OU")){
          string = string.replace("OU", map.get("OU"));
        }
        branchList.add(string);
      }
    }
    try {
      // 以行为单位读取文件内容，一次读一整行
      reader = new BufferedReader(new FileReader(file));
      String tempString = null;
      while ((tempString = reader.readLine()) != null) {
        if(tempString.startsWith("#")){
          continue;
        }else if(tempString.contains("IP")){
          tempString = tempString.replace("IP", ip);
          list.add(tempString);
        }else if(tempString.contains("PW")){
          tempString = tempString.replace("PW", pw);
          list.add(tempString);
        }else if("START_BRANCH".equals(tempString)){
          list.addAll(branchList);
        }else if("START_GLOBAL".equals(tempString)){
          list.addAll(globalList);
        }else{
          list.add(tempString);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
    return list;
  }

  public static void makeConfig(String sysCode, String platCode){
    //ldap-zhong
    String a = "E:/txt/congif.pro1";
    String b = "E:/txt/congif.pro2";
    
    String sysCodeLine = "config.sysCode="+sysCode;
    String platCodeLine = "config.platCode="+platCode;
    String ldapTaskLine = "config.syncToLdapTask=0 0/1 * * * ?";
    String dbTaskLine = "conifg.syncToDbTask=30 0/1 * * * ?";
//    config.syncToLdapOutTask=15 0/1 * * * ?
//    conifg.syncToLdapInTask=45 0/1 * * * ?
    
    String[] ldapZhong = new String[]{sysCodeLine,ldapTaskLine,dbTaskLine};
    String[] zhongYeWu = new String[]{sysCodeLine,platCodeLine,ldapTaskLine,dbTaskLine};
    
    for (String s : ldapZhong) {
      ConfigUtils.appendMethodA(a, s+System.getProperty("line.separator"));
    }
    for (String s : zhongYeWu) {
      ConfigUtils.appendMethodA(b, s+System.getProperty("line.separator"));
    }
    
        
        
  }
  
  public static void main(String[] args) {

    String a = "C:/Users/wpc/Desktop/global.ldif";
    String b = "E:/txt/branch.ldif";
    String c = "E:/txt/slapd.conf";
    // String content = "new append!";
    // //按方法A追加文件
    // ConfigUtils.appendMethodA(fileName, content);
    // ConfigUtils.appendMethodA(fileName, "append end. \n");
    // //显示文件内容
    // ConfigUtils.readFileByLines(fileName);
    // //按方法B追加文件
    // ConfigUtils.appendMethodB(fileName, content);
    // ConfigUtils.appendMethodB(fileName, "append end. \n");
    // //显示文件内容
    // ConfigUtils.readFileByLines(fileName);

//    List<String> list = ConfigUtils.makeGlobalConfig(a, b, "global");
//    List<String> list = ConfigUtils.makeConfig(a, b, "global");
    
//    List<LdapObject> s = new ArrayList<LdapObject>();
//    LdapObject A1 = new LdapObject();
//    A1.setSysCode("1501000000000");
//    A1.setOu("ou=15010101,ou=15010101");
//    LdapObject A2 = new LdapObject();
//    A2.setSysCode("1502000000000");
//    A2.setOu("ou=15010101,ou=15010101");
//    LdapObject B = new LdapObject();
//    B.setSysCode("1501010000000");
//    B.setOu("ou=15010101,ou=15010101");
//    LdapObject C = new LdapObject();
//    C.setSysCode("1501010100000");
//    C.setOu("ou=15010101,ou=15010101");
//    s.add(A1);
//    s.add(A2);
//    s.add(B);
//    s.add(C);
    
//    ConfigUtils.a(s, null);
//    List<String> list = ConfigUtils.makeBranchConfig(s);
    
//    List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
//    Map<String, String> map1 = new HashMap<String, String>();
//    map1.put("IP", "192.168.11.115");
//    map1.put("PW", "openldap");
//    map1.put("OU", "ou=15010101,ou=15010000");
//    Map<String, String> map2 = new HashMap<String, String>();
//    map2.put("IP", "192.168.11.117");
//    map2.put("PW", "openldap");
//    map2.put("OU", "ou=15010102,ou=15010002");
//    lists.add(map1);
//    lists.add(map2);
//    List<String> list = ConfigUtils.makeSlapdConfig("176", "openldap", lists);
//    
//    for (String string : list) {
//      System.out.println(string);
////      ConfigUtils.appendMethodA(c, string+System.getProperty("line.separator"));
//    }
    
    ConfigUtils.makeConfig("123","321");
    
  }

}
