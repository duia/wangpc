/**
 * 文件名：HandshakeInterceptor.java
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

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;

/**
 * <dl>
 * Class Description
 * <dd>项目名称：springmvc
 * <dd>类名称：HandshakeInterceptor
 * <dd>类描述：概述类的作用
 * <dd>创建人：王鹏程
 * <dd>创建时间：2016年6月8日 下午2:53:00
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
public class MyHandshakeInterceptor implements org.springframework.web.socket.server.HandshakeInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(MyHandshakeInterceptor.class);
					
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler webSocketHandler, Exception exception) {
		logger.info("afterHandshake");
	}

	// 进入hander之前的拦截
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
		logger.info("Websocket:用户[ID:"+ ((ServletServerHttpRequest) request).getServletRequest()
						.getSession(false).getAttribute("uid") + "]已经建立连接");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// 标记用户
			Long uid = (Long) session.getAttribute("uid");
			if (uid != null) {
				map.put("uid", uid);
			} else {
				return false;
			}
		}
		return true;
	}

}
