/**
 * 文件名：TestRun.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年5月4日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.times;

/**
 * <dl>
 * Class Description
 * <dd>项目名称：springmvc
 * <dd>类名称：TestRun
 * <dd>类描述：概述类的作用
 * <dd>创建人：王鹏程
 * <dd>创建时间：2016年5月4日 下午4:01:47
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
public class TestRun {

	public void say() {
		System.out.println("真正的业务逻辑运行...");
		try {
			System.out.println("程序处理中...");
			Thread.sleep(3000);
//			MailSenderUtil.send();
//			MyWebSocketHandler.sendMessageToUsers(new TextMessage(DateUtils.getCurrentTime()+":"+"哈哈哈"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("业务逻辑运行完成...");
	}

}
