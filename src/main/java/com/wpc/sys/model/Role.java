/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: 角色表
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class Role extends BaseEntity {

	// 角色名称
	private String roleName;
	// 权限码
	private String roleCode;
	// 角色类型
	private String roleType;
	// 数据范围
	private String dataScope;
	// 是否系统数据
	private String isSys;
	// 是否可用
	private String useable;
	// sort
	private Integer sort;
	// 创建者
	private Long createBy;
	// 创建时间
	private Date createDate;
	// 更新者
	private Long updateBy;
	// 更新时间
	private Date updateDate;
	// 备注信息
	private String remarks;
	// 删除标记
	private String delFlag;
		
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	public String getRoleType() {
		return this.roleType;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}
	
	public String getDataScope() {
		return this.dataScope;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
	
	public String getIsSys() {
		return this.isSys;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}
	
	public String getUseable() {
		return this.useable;
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
		sb.append("Role[");
		sb.append("id=");
		sb.append(id);
		sb.append(", roleName=");
		sb.append(roleName);
		sb.append(", roleCode=");
		sb.append(roleCode);
		sb.append(", roleType=");
		sb.append(roleType);
		sb.append(", dataScope=");
		sb.append(dataScope);
		sb.append(", isSys=");
		sb.append(isSys);
		sb.append(", useable=");
		sb.append(useable);
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
