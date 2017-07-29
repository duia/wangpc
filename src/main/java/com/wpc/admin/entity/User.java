/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.admin.entity;


import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: User
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class User extends BaseEntity {

	// username
	private String username;
	// 账户
	private String account;
	// password
	private String password;
	// 年龄
	private Integer age;
	// updateTime
	private Date updateTime;
		
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getAccount() {
		return this.account;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Integer getAge() {
		return this.age;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("User[");
		sb.append("username=");
		sb.append(username);
		sb.append("account=");
		sb.append(account);
		sb.append("password=");
		sb.append(password);
		sb.append("age=");
		sb.append(age);
		sb.append("updateTime=");
		sb.append(updateTime);
		sb.append("]");
		return sb.toString();
	}
}
