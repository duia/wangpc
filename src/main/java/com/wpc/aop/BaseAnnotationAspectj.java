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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
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
	private static String[] types = { "java.lang.Integer", "java.lang.Double", "java.lang.Float", "java.lang.Long",
			"java.lang.Short", "java.lang.Byte", "java.lang.Boolean", "java.lang.Char", "java.lang.String", "int",
			"double", "long", "short", "byte", "boolean", "char", "float" };
	private static String[] collectionTypes = { "java.util.Map", "java.util.List", "jjava.util.Set" };

	/** 当次参数名称List */
	protected List<String> paramName = null;

	/** 当次参数名和参数值Map */
	protected Map<String, Object> nameAndValueMap = null;

	protected HttpServletRequest request;

	/**
	 * 方法：setRequest <br>
	 * 描述：设置request <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年5月26日 下午6:50:31 <br>
	 */
	protected void setRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		request = sra.getRequest();
	}

	/**
	 * 方法：setParams <br>
	 * 描述：TODO <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年5月23日 下午2:17:51 <br>
	 *
	 * @param joinPoint
	 * @throws Exception
	 */
	protected void setParams(ProceedingJoinPoint joinPoint) throws Exception {
		paramName = getFieldsName(this.getClass(), joinPoint);
		nameAndValueMap = getValue(paramName, joinPoint);
	}

	/**
	 * 方法：getValue <br>
	 * 描述：获取值 <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月20日 下午12:19:24 <br>
	 *
	 * @param paramNames
	 * @param joinPoint
	 * @return
	 */
	protected static Map<String, Object> getValue(List<String> paramNames, JoinPoint joinPoint) {
		Map<String, Object> result = new HashMap<String, Object>();
		Object[] args = joinPoint.getArgs();
		for (int k = 0; k < args.length; k++) {
			Object arg = args[k];
			// 获取对象类型
			if (!judgeBasicType(result, paramNames.get(k), arg)) {
				result.put(paramNames.get(k), getFieldsValue(arg));
			}
		}
		return result;
	}

	/**
	 * 方法：handleJson <br>
	 * 描述：处理json <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年5月31日 下午6:00:28 <br>
	 *
	 * @param result
	 * @param name
	 * @param value
	 * @return
	 */
	protected static boolean handleJson(Map<String, Object> result, String name, Object value) {
		if (value instanceof String) {
			String str = String.valueOf(value);
			if (str.startsWith("{") && str.endsWith("}")) {
				result.put(name, getFieldsValue(JSONObject.stringToValue(str)));
				return true;
			}
		}
		return false;
	}

	/**
	 * 方法：getFieldsName <br>
	 * 描述：得到方法参数的名称 <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月15日 下午5:32:15 <br>
	 *
	 * @param cls
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected static List<String> getFieldsName(Class cls, JoinPoint joinPoint) {
		List<String> result = null;
		try {
			// 获取目标类类型
			String classType = joinPoint.getTarget().getClass().getName();
			// 获取目标类属性
			MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
			// 获取方法名
			String methodName = methodSignature.getName();
			// 获取目标方法
			Method method = methodSignature.getMethod();

			// 通过反射获取目标方法
			Class<?> clazz = Class.forName(classType);

			// 根据方法名和方法入口参数集合获取真实的操作方法
			Method realMethod = clazz.getMethod(methodName, method.getParameterTypes());

			// 获取参数名
			LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
			String[] params = localVariableTableParameterNameDiscoverer.getParameterNames(realMethod);
			if (!ObjectUtils.isEmpty(params)) {
				result = Arrays.asList(params);
			}
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 方法：getFieldsValue <br>
	 * 描述：如果参数是对象得到参数的值 <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月15日 下午5:31:46 <br>
	 *
	 * @param obj
	 * @return
	 */
	protected static Map<String, Object> getFieldsValue(Object obj) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Field> fieldList = new ArrayList<Field>();
		if (!ObjectUtils.isEmpty(obj) && !ObjectUtils.isEmpty(obj.getClass())) {
			Class<? extends Object> objClass = obj.getClass();
			fieldList.addAll(Arrays.asList(objClass.getDeclaredFields()));
			fieldList.addAll(Arrays.asList(objClass.getSuperclass().getDeclaredFields()));
		}
		if (judgeBasicType()) {
			return null;
		}
		for (Field f : fieldList) {
			f.setAccessible(true);
			try {
				if (!ObjectUtils.isEmpty(f.get(obj))) {
					if (Arrays.asList(types).contains(f.getType().getName())) {
						result.put(f.getName(), f.get(obj));
					} else if (Arrays.asList(collectionTypes).contains(f.getType().getName())) {
						result.put(f.getName(), String.valueOf(f.get(obj)));
					} else {
						result.put(f.getName(), String.valueOf(f.get(obj)));
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 方法：getReqeust <br>
	 * 描述：获取request <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月15日 下午6:25:15 <br>
	 *
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		return request;
	}

	/**
	 * 方法：judgeBasicType <br>
	 * 描述：判断基本类型 <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月21日 上午10:35:11 <br>
	 *
	 * @return
	 */
	private static Boolean judgeBasicType() {
		return judgeBasicType(null, null, null);
	}

	/**
	 * 方法：judgeType <br>
	 * 描述：判断基本类型 <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月21日 上午9:33:51 <br>
	 *
	 * @param result
	 * @param name
	 * @param value
	 * @return
	 */
	private static Boolean judgeBasicType(Map<String, Object> result, String name, Object value) {
		if (value instanceof Integer || value instanceof String || value instanceof Double || value instanceof Float
				|| value instanceof Long || value instanceof Boolean || value instanceof Date) {
			if (!ObjectUtils.isEmpty(name) && !ObjectUtils.isEmpty(value)) {
				result.put(name, value);
			}
			return true;
		} else {
			return false;
		}
	}
}
