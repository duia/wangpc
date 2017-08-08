/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: File
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class File extends BaseEntity {

	// fileName
	private String fileName;
	// fileCode
	private String fileCode;
	// filePath
	private String filePath;
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
		
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return this.fileName;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	
	public String getFileCode() {
		return this.fileCode;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return this.filePath;
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
		sb.append("File[");
		sb.append("id=");
		sb.append(id);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", fileCode=");
		sb.append(fileCode);
		sb.append(", filePath=");
		sb.append(filePath);
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
