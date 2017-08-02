/**
 * 文件名：IndexController.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2015年12月31日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2015 
 *
 */
package com.wpc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wpc.annotation.SysLogAnn;
import com.wpc.enums.OperLevel;
import com.wpc.enums.OperType;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpc.times.ScheduleJob;
import com.wpc.times.SchedulerUtil;

/**
 * <dl>
 * Class Description
 * <dd>项目名称：springmvc
 * <dd>类名称：IndexController
 * <dd>类描述：概述类的作用
 * <dd>创建人：王鹏程
 * <dd>创建时间：2015年12月31日 上午9:43:33
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
@Controller
@RequestMapping("/times")
public class TimesController {

	@Autowired
	private Scheduler scheduler;

	@RequestMapping
	@SysLogAnn(operType = OperType.SYSTEM, operLevel = OperLevel.NORM, describe = "跳转")
	public String index(ModelMap model) {
//		int i = 1/0;
		return "times/times";
	}

	@SysLogAnn(operType = OperType.SYSTEM, operLevel = OperLevel.NORM, describe = "添加")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public void add(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		int i = 1/0;
		ScheduleJob job = new ScheduleJob();
		job.setJobId(3L);
		job.setJobName("更新人员数据");
		job.setJobGroup("数据同步");
		job.setJobStatus("1");
		job.setCronExpression("0/5 * * * * ?");
		job.setDescription("每隔多久更新一次人员数据...");
		job.setClassName("com.wpc.times.TestRun");
		job.setMethodName("say");
		try {
			SchedulerUtil.addJob(scheduler, job);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "pause", method = RequestMethod.GET)
	public void pause(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ScheduleJob scheduleJob = new ScheduleJob("更新人员数据", "数据同步");
		SchedulerUtil.pauseJob(scheduler, scheduleJob);
	}

	@RequestMapping(value = "resume", method = RequestMethod.GET)
	public void resume(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ScheduleJob scheduleJob = new ScheduleJob("更新人员数据", "数据同步");
		SchedulerUtil.resumeJob(scheduler, scheduleJob);
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ScheduleJob scheduleJob = new ScheduleJob("更新人员数据", "数据同步");
		SchedulerUtil.deleteJob(scheduler, scheduleJob);
	}

	@SysLogAnn(operType = OperType.SYSTEM, operLevel = OperLevel.NORM, describe = "查询")
	@RequestMapping(value = "running", method = RequestMethod.GET)
	public @ResponseBody List<ScheduleJob> running(HttpServletRequest request, HttpServletResponse response,
					ModelMap model) throws Exception {
		return SchedulerUtil.runningJob(scheduler);
	}

	@RequestMapping(value = "waiting", method = RequestMethod.GET)
	public @ResponseBody List<ScheduleJob> waiting(HttpServletRequest request, HttpServletResponse response,
					ModelMap model) throws Exception {
		return SchedulerUtil.waitingJob(scheduler);
	}

}
