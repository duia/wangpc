/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: 用户-角色
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class UserRole extends BaseEntity {

	// 用户编号
	private Long userId;
	// 角色编号
	private Long roleId;
		
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return this.userId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("UserRole[");
		sb.append("id=");
		sb.append(id);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append("]");
		return sb.toString();
	}
}
