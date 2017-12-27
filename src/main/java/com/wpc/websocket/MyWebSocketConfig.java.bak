/**
 * 文件名：WebSocketConfig.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2016年6月8日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2016 
 *
 */
package com.wpc.websocket;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * <dl>
 * Class Description
 * <dd>项目名称：springmvc
 * <dd>类名称：WebSocketConfig
 * <dd>类描述：概述类的作用
 * <dd>创建人：王鹏程
 * <dd>创建时间：2016年6月8日 下午2:51:36
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
//@Configuration
//@EnableWebSocket // 开启websocket
public class MyWebSocketConfig implements WebSocketConfigurer {

	@Resource
	private MyWebSocketHandler handler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler, "/echo").addInterceptors(new MyHandshakeInterceptor()); // 支持websocket的访问链接 //.setAllowedOrigins("*") 用来设置来自那些域名的请求可访问
		registry.addHandler(handler, "/sockjs/echo").addInterceptors(new MyHandshakeInterceptor()).withSockJS(); // 不支持websocket的访问链接 允许客户端使用SockJS
	}

}
