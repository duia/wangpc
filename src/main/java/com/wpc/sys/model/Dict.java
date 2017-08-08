/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: 字典表
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class Dict extends BaseEntity {

	// 数据值
	private String value;
	// 标签名
	private String label;
	// 类型
	private String type;
	// 描述
	private String description;
	// 排序（升序）
	private Integer sort;
	// 父级编号
	private Long parentId;
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
		
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getSort() {
		return this.sort;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Long getParentId() {
		return this.parentId;
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
		sb.append("Dict[");
		sb.append("id=");
		sb.append(id);
		sb.append(", value=");
		sb.append(value);
		sb.append(", label=");
		sb.append(label);
		sb.append(", type=");
		sb.append(type);
		sb.append(", description=");
		sb.append(description);
		sb.append(", sort=");
		sb.append(sort);
		sb.append(", parentId=");
		sb.append(parentId);
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
