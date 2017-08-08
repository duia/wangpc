/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: Element
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class Element extends BaseEntity {

	// 所属菜单的id
	private Long menuId;
	// 所属菜单id的层级关系
	private String menuIds;
	// elementName
	private String elementName;
	// elementCode
	private String elementCode;
	// elementDesc
	private String elementDesc;
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
		
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	
	public String getMenuIds() {
		return this.menuIds;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	public String getElementName() {
		return this.elementName;
	}

	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}
	
	public String getElementCode() {
		return this.elementCode;
	}

	public void setElementDesc(String elementDesc) {
		this.elementDesc = elementDesc;
	}
	
	public String getElementDesc() {
		return this.elementDesc;
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
		sb.append("Element[");
		sb.append("id=");
		sb.append(id);
		sb.append(", menuId=");
		sb.append(menuId);
		sb.append(", menuIds=");
		sb.append(menuIds);
		sb.append(", elementName=");
		sb.append(elementName);
		sb.append(", elementCode=");
		sb.append(elementCode);
		sb.append(", elementDesc=");
		sb.append(elementDesc);
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
