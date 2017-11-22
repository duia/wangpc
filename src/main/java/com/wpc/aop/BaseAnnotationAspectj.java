/**
 * 文件名：BaseAnnotationAspectj.java <br>
 * 版本信息：TODO <br>
 * 作者：王鹏程 E-mail：wpcfree@qq.com QQ:376205421
 * 日期：2017年3月15日-下午2:12:49<br>
 * Copyright (c) 2017 王鹏程-版权所有<br>
 *
 */
package com.wpc.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wpc.util.script.AbstractScriptParser;
import com.wpc.util.script.SpringELParser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * 类名称：BaseAnnotationAspectj <br>
 * 类描述：自定义SKU注解拦截器 <br>
 * 创建人：王鹏程 <br>
 * 修改人：王鹏程 <br>
 * 修改时间：2017年3月15日 下午2:12:49 <br>
 * 修改备注：TODO <br>
 *
 */
public class BaseAnnotationAspectj {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	Object[] getArgs(ProceedingJoinPoint joinPoint) {
		return joinPoint.getArgs();
	}

	Class getTargetClass(ProceedingJoinPoint joinPoint) {
		return joinPoint.getTarget().getClass();
	}

	Method getMethod(ProceedingJoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;
		return methodSignature.getMethod();
	}

	/**
	 * 直接加载数据（加载后的数据不往缓存放）
	 * @param joinPoint
	 * @return Object
	 * @throws Throwable
	 */
	Object getData(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			long startTime = System.currentTimeMillis();
			Object result = joinPoint.proceed();
			long useTime = System.currentTimeMillis() - startTime;
			String className = getTargetClass(joinPoint).getName();
			logger.info("{}.{}, 耗时:{}ms", className, getMethod(joinPoint).getName(), useTime);
			return result;
		} catch(Throwable e) {
			throw e;
		}
	}

}
