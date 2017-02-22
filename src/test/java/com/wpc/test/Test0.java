/**
 * 文件名：Test0.java
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
package com.wpc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wpc.times.DataWorkContext;
import com.wpc.times.QuartzJobFactory;
import com.wpc.times.ScheduleJob;

import junit.framework.TestCase;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：Test0
 *  <dd> 类描述：概述类的作用 
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年4月29日 下午4:29:21
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/applicationContext.xml")
public class Test0 extends TestCase {

  @Autowired
  private SchedulerFactoryBean schedulerFactoryBean;
  
  @Test
  public void test1() throws Exception {
    //schedulerFactoryBean 由spring创建注入
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    //这里获取任务信息数据
    List<ScheduleJob> jobList = DataWorkContext.getAllJob();
    for (ScheduleJob job : jobList) {
      TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
      //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
      CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
      //不存在，创建一个
      if (null == trigger) {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
          .withIdentity(job.getJobName(), job.getJobGroup()).build();
        jobDetail.getJobDataMap().put("scheduleJob", job);
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
          .getCronExpression());
        //按新的cronExpression表达式构建一个新的trigger
        trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
      } else {
        // Trigger已存在，那么更新相应的定时设置
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
          .getCronExpression());
        //按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
          .withSchedule(scheduleBuilder).build();
        //按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
      }
    }
    ScheduleJob j = DataWorkContext.getJob("100010");
  }
  
  /**
   * 
   * <b>Method test2</b>
   * <dd>方法作用：计划中的任务
   * @throws Exception
   * @since Met 1.0
   */
  @Test
  public void test2() throws Exception {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
    List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
    for (JobKey jobKey : jobKeys) {
        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
        for (Trigger trigger : triggers) {
            ScheduleJob job = new ScheduleJob();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
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
  }
  
  /**
   * 
   * <b>Method test3</b>
   * <dd>方法作用：运行中的任务
   * @throws Exception
   * @since Met 1.0
   */
  @Test
  public void test3() throws Exception {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
    List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
    for (JobExecutionContext executingJob : executingJobs) {
        ScheduleJob job = new ScheduleJob();
        JobDetail jobDetail = executingJob.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        Trigger trigger = executingJob.getTrigger();
        job.setJobName(jobKey.getName());
        job.setJobGroup(jobKey.getGroup());
        job.setDescription("触发器:" + trigger.getKey());
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
  
  /**
   * 
   * <b>Method test4</b>
   * <dd>方法作用：暂停任务
   * @throws Exception
   * @since Met 1.0
   */
//  @Test
//  public void test4() throws Exception {
//
//
//    
//
//
//  }
  
  /**
   * 
   * <b>Method test5</b>
   * <dd>方法作用：恢复任务
   * @throws Exception
   * @since Met 1.0
   */
//  @Test
//  public void test5() throws Exception {
//
//
//    Scheduler scheduler = schedulerFactoryBean.getScheduler();
//    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
//    scheduler.resumeJob(jobKey);
//
//
//  }
  
  /**
   * 
   * <b>Method test6</b>
   * <dd>方法作用：删除任务
   * @throws Exception
   * @since Met 1.0
   */
//  @Test
//  public void test6() throws Exception {
//
//
//    Scheduler scheduler = schedulerFactoryBean.getScheduler();
//    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
//    scheduler.deleteJob(jobKey);
//
//
//  }
  
  /**
   * 
   * <b>Method test7</b>
   * <dd>方法作用：立即运行任务
   * @throws Exception
   * @since Met 1.0
   */
//  @Test
//  public void test7() throws Exception {
//
//
//    Scheduler scheduler = schedulerFactoryBean.getScheduler();
//    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
//    scheduler.triggerJob(jobKey);
//
//
//  }
  
  /**
   * 
   * <b>Method test8</b>
   * <dd>方法作用：更新任务的时间表达式
   * @throws Exception
   * @since Met 1.0
   */
//  @Test
//  public void test8() throws Exception {
//
//
//    Scheduler scheduler = schedulerFactoryBean.getScheduler();
//    TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),
//        scheduleJob.getJobGroup());
//    //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
//    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//    //表达式调度构建器
//    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob
//        .getCronExpression());
//    //按新的cronExpression表达式重新构建trigger
//    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
//        .withSchedule(scheduleBuilder).build();
//    //按新的trigger重新设置job执行
//    scheduler.rescheduleJob(triggerKey, trigger);
//
//
//  }
  
}
