/**
 * 文件名：DataWorkContext.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <dl>  Class Description
 *  <dd> 项目名称：springmvc
 *  <dd> 类名称：DataWorkContext
 *  <dd> 类描述：概述类的作用
 *  <dd> 创建人：王鹏程
 *  <dd> 创建时间：2016年4月29日 下午4:22:53
 *  <dd> 修改人：无
 *  <dd> 修改时间：无
 *  <dd> 修改备注：无
 * </dl>
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
public class DataWorkContext {
  private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();
  static {
    for (int i = 0; i < 5; i++) {
      ScheduleJob job = new ScheduleJob();
      job.setJobId(Long.valueOf(i));
      job.setJobName("data_import" + i);
      job.setJobGroup("dataWork");
      job.setJobStatus("1");
      job.setCronExpression("0/"+(5+2*i)+" * * * * ?");
      job.setDescription("数据导入任务");
      addJob(job);
    }
  }
  /**
   * 添加任务
   * @param scheduleJob
   */
  public static void addJob(ScheduleJob scheduleJob) {
    jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
  }
  
  /**
   * 获取任务
   *
   * @param jobId
   * @return
   */
  public static ScheduleJob getJob(String jobId) {
      return jobMap.get(jobId);
  }

  /**
   * 获取所有任务
   *
   * @return
   */
  public static List<ScheduleJob> getAllJob(){
    List<ScheduleJob> list = new ArrayList<ScheduleJob>();
    for (String s : jobMap.keySet()) {
      list.add(jobMap.get(s));
    }
    return list;
  }
}
