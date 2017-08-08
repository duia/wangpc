/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;
import com.wpc.common.base.entity.TreeEntity;

import java.util.Date;

/**
 * 功能描述: 机构表
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class Department extends TreeEntity<Department> {

	// 名称
	private String name;
	// 区域编码
	private String code;
	// 机构类型
	private String type;
	// 机构等级
	private String grade;
	// 联系地址
	private String address;
	// 邮政编码
	private String zipCode;
	// 负责人
	private String master;
	// 电话
	private String phone;
	// 传真
	private String fax;
	// 邮箱
	private String email;
	// 是否启用
	private String useable;
	// 主负责人
	private String primaryPerson;
	// 副负责人
	private String deputyPerson;

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getGrade() {
		return this.grade;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getZipCode() {
		return this.zipCode;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	public String getMaster() {
		return this.master;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return this.phone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getFax() {
		return this.fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}
	
	public String getUseable() {
		return this.useable;
	}

	public void setPrimaryPerson(String primaryPerson) {
		this.primaryPerson = primaryPerson;
	}
	
	public String getPrimaryPerson() {
		return this.primaryPerson;
	}

	public void setDeputyPerson(String deputyPerson) {
		this.deputyPerson = deputyPerson;
	}
	
	public String getDeputyPerson() {
		return this.deputyPerson;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Department[");
		sb.append("id=");
		sb.append(id);
		sb.append(", parentId=");
		sb.append(parentId);
		sb.append(", parentIds=");
		sb.append(parentIds);
		sb.append(", name=");
		sb.append(name);
		sb.append(", sort=");
		sb.append(sort);
		sb.append(", code=");
		sb.append(code);
		sb.append(", type=");
		sb.append(type);
		sb.append(", grade=");
		sb.append(grade);
		sb.append(", address=");
		sb.append(address);
		sb.append(", zipCode=");
		sb.append(zipCode);
		sb.append(", master=");
		sb.append(master);
		sb.append(", phone=");
		sb.append(phone);
		sb.append(", fax=");
		sb.append(fax);
		sb.append(", email=");
		sb.append(email);
		sb.append(", useable=");
		sb.append(useable);
		sb.append(", primaryPerson=");
		sb.append(primaryPerson);
		sb.append(", deputyPerson=");
		sb.append(deputyPerson);
		sb.append(", createBy=");
		sb.append(createBy);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", updateBy=");
		sb.append(updateBy);
		sb.append(", updateDate=");
		sb.append(updateDate);
		sb.append(", remarks=");
		sb.append(remarks);
		sb.append(", delFlag=");
		sb.append(delFlag);
		sb.append("]");
		return sb.toString();
	}
}
