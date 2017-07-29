/**
 * 文件名：RestController.java
 * <p>
 * 描述：此处填写文件的描述信息
 * <p>
 * 日期：2016年2月16日
 * <p>
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 * <p>
 * Copyright(C) WEAVER Corporation 2016
 */
package com.wpc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wpc.admin.entity.User;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：RestController
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年2月16日 上午10:37:30
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/rest")
public class RestDemoController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") long id) {
        System.out.println("get" + id);
        User user = new User();
        user.setId(id);
        user.setUsername("wpc");
        user.setPassword("123");
        return user;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getAll() {
        System.out.println("getAll");
        List<User> list = new ArrayList<User>();
        User user = null;
        user = new User();
        user.setUsername("wpc1");
        user.setPassword("123");
        list.add(user);
        user = new User();
        user.setUsername("wpc2");
        user.setPassword("123");
        list.add(user);
        user = new User();
        user.setUsername("wpc3");
        user.setPassword("123");
        list.add(user);
        return list;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String post(@PathVariable("id") int id) {
        System.out.println("post" + id);
        return "POST";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String put(@PathVariable("id") int id) {
        System.out.println("put" + id);
        return "PUT";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String detele(@PathVariable("id") int id) {
        System.out.println("detele" + id);
        return "DELETE";
    }

}
