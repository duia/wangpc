package com.wpc.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MyExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		HandlerMethod method = null != handler?method = (HandlerMethod) handler : null;//获取抛出异常的方法对象
		if(method != null){
			String accept = request.getHeader("accept");
			String requestType = request.getHeader("X-Requested-With");
			// ajax 请求
			if (accept.contains("application/json") || (requestType != null && requestType.contains("XMLHttpRequest"))) {
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				AjaxResult result = AjaxResult.error();
				result.setMsg(ex.getLocalizedMessage());
				try{
					PrintWriter writer =  response.getWriter();
					writer.write(new ObjectMapper().writeValueAsString(result));
					writer.flush();
				}catch (IOException e){
					throw new RuntimeException(e);
				}
				return null;
			} else {
				ModelAndView mv = new ModelAndView();
				mv.addObject("errorMsg", ex.getLocalizedMessage());
				mv.setViewName("../500");//Get方法,跳转到500页面
				return mv;//500
			}
		}else{
			throw new NullPointerException("MyExceptionResolver中HandlerMethod为空!");
		}
	}

}
