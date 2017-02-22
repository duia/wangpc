/**
 * 文件名：JobTest.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年5月3日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.times;

import org.quartz.Scheduler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <dl>
 * Class Description
 * <dd>项目名称：springmvc
 * <dd>类名称：JobTest
 * <dd>类描述：概述类的作用
 * <dd>创建人：王鹏程
 * <dd>创建时间：2016年5月3日 上午10:09:58
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
//@Component
public class JobTest implements InitializingBean {

	@Autowired
	private Scheduler scheduler;

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
	 * <b>Overriding Method afterPropertiesSet</b>
	 * <dd>方法作用：(这里用一句话描述这个方法的作用)
	 * 
	 * @throws Exception
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 * @since Met 1.0
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

		// 这里获取任务信息数据
		ScheduleJob job = new ScheduleJob();
		job.setJobId(1L);
		job.setJobName("下载文件邮件发送");
		job.setJobGroup("邮件发送组");
		job.setJobStatus("1");
		job.setCronExpression("0 * * * * ?");
		job.setDescription("文件导出成功后发送下载文件邮件给某人...");
		job.setClassName("com.wpc.times.TestRun");
		job.setMethodName("say");
		SchedulerUtil.addJob(scheduler, job);
//		job = new ScheduleJob();
//		job.setJobId(2L);
//		job.setJobName("有需要處理的邮件发送");
//		job.setJobGroup("邮件发送组");
//		job.setJobStatus("1");
//		job.setCronExpression("0/25 * * * * ?");
//		job.setDescription("有需要某人處理的信息邮件给某人...");
//		job.setClassName("com.wpc.times.TestRun");
//		job.setMethodName("say");
//		SchedulerUtil.addJob(scheduler, job);

	}

}
