/**
 * 文件名：ScheduleJob.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年4月29日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.times;

import java.util.Date;

/**
 * <dl>
 * Class Description
 * <dd>项目名称：springmvc
 * <dd>类名称：ScheduleJob
 * <dd>类描述：概述类的作用
 * <dd>创建人：王鹏程
 * <dd>创建时间：2016年4月29日 下午4:17:40
 * <dd>修改人：无
 * <dd>修改时间：无
 * <dd>修改备注：无
 * </dl>
 * 
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class ScheduleJob {

	/** 任务调度的参数key */
	public static String JOB_PARAM_KEY = "scheduleJob";
	/** 任务id */
	private Long jobId;
	/** 任务名称 */
	private String jobName;
	/** 任务别名 */
    private String aliasName;
	/** 任务分组 */
	private String jobGroup;
	/** 触发器 */
    private String jobTrigger;
	/** 任务状态 0禁用 1启用 2删除 */
	private String jobStatus;
	/** 任务运行时间表达式 */
	private String cronExpression;
	/** 任务描述 */
	private String description;
	/** 创建时间 */
    private Date jobCreate;
    /** 修改时间 */
    private Date jobModify;
    /** 任务执行url */
    private String url;
    
    private String className;
    private String methodName;
    
	public ScheduleJob() {
		super();
	}

	public ScheduleJob(String jobName, String jobGroup) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
	}
	
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobTrigger() {
		return jobTrigger;
	}
	public void setJobTrigger(String jobTrigger) {
		this.jobTrigger = jobTrigger;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getJobCreate() {
		return jobCreate;
	}
	public void setJobCreate(Date jobCreate) {
		this.jobCreate = jobCreate;
	}
	public Date getJobModify() {
		return jobModify;
	}
	public void setJobModify(Date jobModify) {
		this.jobModify = jobModify;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
