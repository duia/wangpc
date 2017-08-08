/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: Permission
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class Permission extends BaseEntity {

	// parentId
	private Long parentId;
	// parentIds
	private String parentIds;
	// resourceId
	private Long resourceId;
	// permissionName
	private String permissionName;
	// 权限码
	private String permissionCode;
	// 权限类型
	private String permissionType;
	// sort
	private Integer sort;
	// createBy
	private Long createBy;
	// createDate
	private Date createDate;
	// updateBy
	private Long updateBy;
	// updateDate
	private Date updateDate;
	// remarks
	private String remarks;
	// delFlag
	private String delFlag;
		
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getParentIds() {
		return this.parentIds;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	public Long getResourceId() {
		return this.resourceId;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	
	public String getPermissionCode() {
		return this.permissionCode;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}
	
	public String getPermissionType() {
		return this.permissionType;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getSort() {
		return this.sort;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	
	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	
	public Long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getRemarks() {
		return this.remarks;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public String getDelFlag() {
		return this.delFlag;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Permission[");
		sb.append("id=");
		sb.append(id);
		sb.append(", parentId=");
		sb.append(parentId);
		sb.append(", parentIds=");
		sb.append(parentIds);
		sb.append(", resourceId=");
		sb.append(resourceId);
		sb.append(", permissionName=");
		sb.append(permissionName);
		sb.append(", permissionCode=");
		sb.append(permissionCode);
		sb.append(", permissionType=");
		sb.append(permissionType);
		sb.append(", sort=");
		sb.append(sort);
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
