/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: SysLog
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class SysLog extends BaseEntity {

	// 操作名称
	private String operName;
	// 操作参数
	private String operParam;
	// 操作类型
	private String operType;
	// 操作描述
	private String operDescribe;
	// 操作级别
	private Integer operLevel;
	// 操作时间
	private Date operTime;
	// 操作人ID
	private Long userId;
	// 操作人姓名
	private String userName;
	// 操作用户的IP地址
	private String remoteAddr;
	// 操作的URI
	private String requestUri;
	// 操作的方式
	private String method;
	// 操作用户代理信息
	private String userAgent;
	// 异常信息
	private String exception;
		
	public void setOperName(String operName) {
		this.operName = operName;
	}
	
	public String getOperName() {
		return this.operName;
	}

	public void setOperParam(String operParam) {
		this.operParam = operParam;
	}
	
	public String getOperParam() {
		return this.operParam;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}
	
	public String getOperType() {
		return this.operType;
	}

	public void setOperDescribe(String operDescribe) {
		this.operDescribe = operDescribe;
	}
	
	public String getOperDescribe() {
		return this.operDescribe;
	}

	public void setOperLevel(Integer operLevel) {
		this.operLevel = operLevel;
	}
	
	public Integer getOperLevel() {
		return this.operLevel;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	
	public Date getOperTime() {
		return this.operTime;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return this.userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
	public String getRemoteAddr() {
		return this.remoteAddr;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	
	public String getRequestUri() {
		return this.requestUri;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getMethod() {
		return this.method;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public String getUserAgent() {
		return this.userAgent;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
	public String getException() {
		return this.exception;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("SysLog[");
		sb.append("id=");
		sb.append(id);
		sb.append(", operName=");
		sb.append(operName);
		sb.append(", operParam=");
		sb.append(operParam);
		sb.append(", operType=");
		sb.append(operType);
		sb.append(", operDescribe=");
		sb.append(operDescribe);
		sb.append(", operLevel=");
		sb.append(operLevel);
		sb.append(", operTime=");
		sb.append(operTime);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", remoteAddr=");
		sb.append(remoteAddr);
		sb.append(", requestUri=");
		sb.append(requestUri);
		sb.append(", method=");
		sb.append(method);
		sb.append(", userAgent=");
		sb.append(userAgent);
		sb.append(", exception=");
		sb.append(exception);
		sb.append("]");
		return sb.toString();
	}
}
