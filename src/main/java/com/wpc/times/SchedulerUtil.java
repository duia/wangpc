/**
 * 文件名：SchedulerUtil.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * <dl>
 * Class Description
 * <dd>项目名称：springmvc
 * <dd>类名称：SchedulerUtil
 * <dd>类描述：概述类的作用
 * <dd>创建人：王鹏程
 * <dd>创建时间：2016年5月3日 上午9:14:44
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
public class SchedulerUtil {

	/**
	 * 
	 * @Title: getTriggerKey   
	 * @Description: 获取触发器key
	 * @param scheduleJob
	 * @return TriggerKey  
	 * @author: wangpengcheng      
	 * @throws
	 */
	public static TriggerKey getTriggerKey(ScheduleJob scheduleJob) {
        return TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }
	
	/**
	 * 
	 * @Title: getCronTrigger   
	 * @Description: 获取表达式触发器
	 * @param scheduler
	 * @param scheduleJob
	 * @return
	 * @throws SchedulerException CronTrigger  
	 * @author: wangpengcheng      
	 */
	public static CronTrigger getCronTrigger(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        return (CronTrigger) scheduler.getTrigger(triggerKey);
    }
	
	/**
	 * 
	 * @Title: getJobKey   
	 * @Description: 获取jobKey 
	 * @param scheduleJob
	 * @return JobKey  
	 * @author: wangpengcheng      
	 * @throws
	 */
	public static JobKey getJobKey(ScheduleJob scheduleJob) {
		return JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
	}
	
	/**
	 * 
	 * <b>Method pauseJob</b>
	 * <dd>方法作用：暂停任务
	 * 
	 * @throws Exception
	 * @since Met 1.0
	 */
	public static void addJob(Scheduler scheduler, ScheduleJob scheduleJob) throws Exception {
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 不存在，创建一个
		if (null == trigger) {
			//构建job信息
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
							.withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).build();
			// 放入参数，运行时的方法可以获取
			jobDetail.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, scheduleJob);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
							.withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	/**
	 * 
	 * <b>Method pauseJob</b>
	 * <dd>方法作用：暂停任务
	 * 
	 * @throws Exception
	 * @since Met 1.0
	 */
	public static void pauseJob(Scheduler scheduler, ScheduleJob scheduleJob) throws Exception {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 
	 * <b>Method resumeJob</b>
	 * <dd>方法作用：恢复任务
	 * 
	 * @throws Exception
	 * @since Met 1.0
	 */
	public static void resumeJob(Scheduler scheduler, ScheduleJob scheduleJob) throws Exception {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 
	 * <b>Method deleteJob</b>
	 * <dd>方法作用：删除任务
	 * 
	 * @throws Exception
	 * @since Met 1.0
	 */
	public static void deleteJob(Scheduler scheduler, ScheduleJob scheduleJob) throws Exception {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);
	}

	/**
	 * 
	 * <b>Method triggerJob</b>
	 * <dd>方法作用：立即运行一次任务
	 * 
	 * @throws Exception
	 * @since Met 1.0
	 */
	public static void triggerJob(Scheduler scheduler, ScheduleJob scheduleJob) throws Exception {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 
	 * <b>Method rescheduleJob</b>
	 * <dd>方法作用：更新任务的时间表达式
	 * 
	 * @throws Exception
	 * @since Met 1.0
	 */
	public static void rescheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) throws Exception {
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		// 按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		
		Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        // 忽略状态为PAUSED的任务，解决集群环境中在其他机器设置定时任务为PAUSED状态后，集群环境启动另一台主机时定时任务全被唤醒的bug
        if(!triggerState.name().equalsIgnoreCase("PAUSED")){
        	// 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
	}

	/**
	 * 
	 * <b>Method waitingJob</b>
	 * <dd>方法作用：计划中的任务
	 * 
	 * @throws Exception
	 * @since Met 1.0
	 */
	public static List<ScheduleJob> waitingJob(Scheduler scheduler) throws Exception {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setJobTrigger(trigger.getKey().getName());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 
	 * <b>Method runningJob</b>
	 * <dd>方法作用：运行中的任务
	 * 
	 * @throws Exception
	 * @since Met 1.0
	 */
	public static List<ScheduleJob> runningJob(Scheduler scheduler) throws Exception {
		
		// 存放结果集
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		// 获取scheduler中的JobGroupName
        for (String group : scheduler.getJobGroupNames()){
            // 获取JobKey 循环遍历JobKey
            for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(group))){
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                JobDataMap jobDataMap = jobDetail.getJobDataMap();
                ScheduleJob scheduleJob = (ScheduleJob) jobDataMap.get(ScheduleJob.JOB_PARAM_KEY);
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                Trigger trigger = triggers.iterator().next();
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                scheduleJob.setJobTrigger(trigger.getKey().getName());
                scheduleJob.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    scheduleJob.setCronExpression(cronExpression);
                }
                // 获取正常运行的任务列表
//                if(triggerState.name().equalsIgnoreCase("NORMAL")){
                    jobList.add(scheduleJob);
//                }
            }
        }
		
		/** 非集群环境获取正在执行的任务列表 */
		/*List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			ScheduleJob job = new ScheduleJob();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setDesc("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}*/
		return jobList;
	}

}
