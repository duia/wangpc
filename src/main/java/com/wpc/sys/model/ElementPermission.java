/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: ElementPermission
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class ElementPermission extends BaseEntity {

	// elementId
	private Long elementId;
	// permissionId
	private Long permissionId;
		
	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}
	
	public Long getElementId() {
		return this.elementId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	public Long getPermissionId() {
		return this.permissionId;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("ElementPermission[");
		sb.append("id=");
		sb.append(id);
		sb.append(", elementId=");
		sb.append(elementId);
		sb.append(", permissionId=");
		sb.append(permissionId);
		sb.append("]");
		return sb.toString();
	}
}
