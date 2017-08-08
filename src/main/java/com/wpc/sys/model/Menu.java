/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: 菜单表
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class Menu extends BaseEntity {

	// 父级编号
	private Long parentId;
	// 所有父级编号
	private String parentIds;
	// 名称
	private String menuName;
	// 权限标识
	private String menuCode;
	// 链接
	private String href;
	// 目标
	private String target;
	// 图标
	private String icon;
	// 是否在菜单中显示
	private String isActive;
	// 排序
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

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	public String getMenuCode() {
		return this.menuCode;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	public String getHref() {
		return this.href;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getTarget() {
		return this.target;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getIcon() {
		return this.icon;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public String getIsActive() {
		return this.isActive;
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
		sb.append("Menu[");
		sb.append("id=");
		sb.append(id);
		sb.append(", parentId=");
		sb.append(parentId);
		sb.append(", parentIds=");
		sb.append(parentIds);
		sb.append(", menuName=");
		sb.append(menuName);
		sb.append(", menuCode=");
		sb.append(menuCode);
		sb.append(", href=");
		sb.append(href);
		sb.append(", target=");
		sb.append(target);
		sb.append(", icon=");
		sb.append(icon);
		sb.append(", isActive=");
		sb.append(isActive);
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
