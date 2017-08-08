/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: FilePermission
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class FilePermission extends BaseEntity {

	// fileId
	private Long fileId;
	// permissionId
	private Long permissionId;
		
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	public Long getFileId() {
		return this.fileId;
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
		sb.append("FilePermission[");
		sb.append("id=");
		sb.append(id);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", permissionId=");
		sb.append(permissionId);
		sb.append("]");
		return sb.toString();
	}
}
