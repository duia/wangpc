/**
 * 文件名：CacheAnnotationAspectj.java <br>
 * 版本信息：TODO <br>
 * 作者：王鹏程 E-mail：wpcfree@qq.com QQ:376205421
 * 日期：2017年5月8日-下午5:32:56<br>
 * Copyright (c) 2017 王鹏程-版权所有<br>
 *
 */
package com.wpc.aop;

import com.wpc.annotation.CacheAnn;
import com.wpc.cache.AbstractCache;
import com.wpc.cache.WpcCache;
import com.wpc.enums.ECacheDataSource;
import com.wpc.util.base.ObjectUtils;
import com.wpc.util.encrypt.Md5Utils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * 类名称：CacheAnnotationAspectj <br>
 * 类描述：TODO <br>
 * 创建人：王鹏程 <br>
 * 修改人：王鹏程 <br>
 * 修改时间：2017年5月8日 下午5:32:56 <br>
 * 修改备注：TODO <br>
 *
 */
@Aspect
@Component
public class CacheAnnotationAspectj extends BaseAnnotationAspectj {

	private static final Logger LOGGER = Logger.getLogger(CacheAnnotationAspectj.class);

	@Autowired
	private WpcCache wpcCache;

	private AbstractCache cache;

	/** 存放数据的Field */
	public static final String FIELD_DATA = "data";

	/** 存放编码前的的Field内容 */
	public static final String FIELD_DECODE_FIELD = "field";

	/**
	 * 标题：构造器 <br>
	 * 描述：TODO <br>
	 * 作者：王鹏程 E-mail：wpcfree@qq.com QQ:376205421
	 * 日期： 2017年6月1日 上午10:19:57 <br>
	 *
	 * @param eCacheDataSource
	 *            缓存源
	 */
	public void setCacheDataSource(ECacheDataSource eCacheDataSource) {
		switch (eCacheDataSource) {
			case WPC:
				cache = wpcCache;
				break;
			default:
				break;
		}
	}

	/**
	 * 方法：around <br>
	 * 描述： 配置环绕通知,使用在方法aspect()上注册的切入点 缓存公用拦截器 <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月20日 上午11:52:33 <br>
	 *
	 * @param joinPoint
	 * @param cacheAnnotation
	 * @return
	 */
	@Around("execution(* com.wpc..*.*(..)) and @annotation(cacheAnnotation))")
	public Object around(ProceedingJoinPoint joinPoint, CacheAnn cacheAnnotation) {
		try {

			// 如果组编号为空则直接执行方法
			if (ObjectUtils.isEmpty(cacheAnnotation.groupKey())) {
				return joinPoint.proceed();
			}
			// 如果参数为空则直接执行方法
			if (ObjectUtils.isEmpty(joinPoint.getArgs()) && ObjectUtils.isEmpty(cacheAnnotation.customKey())) {
				return joinPoint.proceed();
			}

			// 如果入口参数
			if (ObjectUtils.isEmpty(joinPoint.getArgs()) && !ObjectUtils.isEmpty(cacheAnnotation.appointFieldKey())) {
				return joinPoint.proceed();
			}

			//设置缓存源
			setCacheDataSource(cacheAnnotation.eCacheDataSource());
			// 判断是否链接缓存
			if (cache.ping()) {
				// 设置入口参数值
				super.setParams(joinPoint);

				// 如果是清除操作,则直接根据分组KEY进行删除
				if (cacheAnnotation.isClean()) {
					for (String gourpKeyTemp : cacheAnnotation.groupKey()) {
						gourpKeyTemp = getGroupName(gourpKeyTemp);
						// 为了尽可能精确按照组编号删除,前后加连接符查询
						gourpKeyTemp = cacheAnnotation.eCachePrefix().getDes() + AbstractCache.SYMBOL_CONNECTOR
								+ gourpKeyTemp + AbstractCache.SYMBOL_CONNECTOR;
						Set<String> keySet = cache.keys("*" + gourpKeyTemp + "*");
						cache.del(keySet.toArray(new String[0]));
					}
				} else if (cacheAnnotation.groupKey().length == 1) {
					// 如果要存取缓存 groupKey必须唯一不能是数组
					return execute(joinPoint, cacheAnnotation);
				}

			}
			return joinPoint.proceed();
		} catch (Throwable e) {
			try {
				return joinPoint.proceed();
			} catch (Throwable e1) {
				return null;
			}
		}
	}

	/**
	 * 方法：getGroupName <br>
	 * 描述：处理组ID确认是否为动态组ID <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年5月23日 下午2:30:14 <br>
	 *
	 * @param groupName
	 * @return
	 */
	protected String getGroupName(String groupName) {
		if (!ObjectUtils.isEmpty(groupName)) {
			if (groupName.startsWith("{") && groupName.endsWith("}")) {
				groupName = groupName.replace("{", "").replace("}", "");
				if (nameAndValueMap.containsKey(groupName)) {
					return String.valueOf(nameAndValueMap.get(groupName));
				}
			}
			return groupName;
		}
		return null;
	}

	/**
	 * 方法：execute <br>
	 * 描述：执行缓存操作 <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月23日 下午4:02:45 <br>
	 *
	 * @param joinPoint
	 * @param cacheAnnotation
	 * @return
	 * @throws Throwable
	 */
	protected Object execute(ProceedingJoinPoint joinPoint, CacheAnn cacheAnnotation) throws Throwable {
		String key = cacheAnnotation.eCachePrefix().getDes() + AbstractCache.SYMBOL_CONNECTOR
				+ getGroupName(cacheAnnotation.groupKey()[0]);
		String targetClassName = joinPoint.getTarget().getClass().getName();
		String targetMethodName = joinPoint.getSignature().getName();
		String fieldPre = targetClassName + AbstractCache.SYMBOL_CONNECTOR + targetMethodName + AbstractCache.SYMBOL_CONNECTOR;
		String field = null;
		Object value = null;
		// 如果要存取缓存 groupKey必须唯一不能是数组
		if (!ObjectUtils.isEmpty(cacheAnnotation.customKey())) {
			// 如果自定义key不为空,则个当前KEY临时变量复制
			field = cacheAnnotation.customKey();
		} else if (!ObjectUtils.isEmpty(joinPoint.getArgs())) {
			field = filterKey(joinPoint, cacheAnnotation.appointFieldKey());
		}
		if (ObjectUtils.isEmpty(field)) {
			return joinPoint.proceed();
		}
		// 新增Key前缀
		field = fieldPre + field;
		String realKey = assembleKey(key, encodeField(field));
		if (cache.exists(realKey)) {
			// 如果缓存存在则直接从缓存取
			// 获取方法的返回类型,让缓存可以返回正确的类型
			// Class<?> returnType = ((MethodSignature)
			// joinPoint.getSignature()).getReturnType();
			value = cache.hGetObj(realKey, FIELD_DATA);
			LOGGER.info("缓存获取数据...");
		}
		if (ObjectUtils.isEmpty(value)) {
			LOGGER.info("缓存获取数据为空,执行具体方法...");
			// 如果返回值为空则执行具体方法获取返回值
			value = joinPoint.proceed();
			if (!ObjectUtils.isEmpty(value)) {
				LOGGER.info("缓存获取数据为空,执行具体方法，返回结果不为空,存入缓存...");
				// 如果执行具体方法获取的返回值不为空则放入缓存
				set(key, field, value, cacheAnnotation.cacheTime() * 1000 * 60);
			}
		}
		return value;

	}

	/**
	 * 方法：filterKey <br>
	 * 描述：获取指定字段作为KEY <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月23日 下午4:24:47 <br>
	 *
	 * @param joinPoint
	 * @param appointFieldKey
	 * @return
	 */
	protected String filterKey(ProceedingJoinPoint joinPoint, String... appointFieldKey) throws Exception {
		boolean returnFlag = false;
		Map<String, Object> newMap = new HashMap<String, Object>();
		if (!ObjectUtils.isEmpty(nameAndValueMap)) {
			if (!ObjectUtils.isEmpty(appointFieldKey) && !ObjectUtils.isEmpty(appointFieldKey[0])) {
				// 如果指定字段不为空则，符合要求的组装为KEY;
				for (String appointFieldKeyTemp : appointFieldKey) {
					if (nameAndValueMap.containsKey(appointFieldKeyTemp)) {
						newMap.put(appointFieldKeyTemp, nameAndValueMap.get(appointFieldKeyTemp));
						returnFlag = true;
					} else {
						// 如果有一个指定字段不包含的话直接返回
						returnFlag = false;
						break;
					}
				}
			} else {
				returnFlag = true;
				newMap.putAll(nameAndValueMap);
			}
		}
		if (returnFlag && !ObjectUtils.isEmpty(newMap)) {

			return newMap.toString();
		}
		return null;
	}

	/**
	 * 方法：encodeField <br>
	 * 描述：获取编码后的field <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月23日 下午5:11:41 <br>
	 *
	 * @param field
	 * @return
	 */
	public String encodeField(String field) {
		return Md5Utils.getMD5(field);
	}

	/**
	 * 方法：assembleKey <br>
	 * 描述：组装Key <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月23日 下午5:03:51 <br>
	 *
	 * @param key
	 * @param field
	 * @return
	 */
	public String assembleKey(String key, String field) {
		return key + AbstractCache.SYMBOL_CONNECTOR + field;
	}

	/**
	 * 方法：set <br>
	 * 描述：设置方法 <br>
	 * 作者：王鹏程 E-mail:wpcfree@qq.com QQ:376205421
	 * 日期： 2017年3月23日 下午3:14:51 <br>
	 *
	 * @param key
	 * @param field
	 * @param o
	 * @param liveTime
	 */
	public void set(final String key, final String field, Object o, final long liveTime) {// 设置缓存field编码前字段
		String newKey = assembleKey(key, encodeField(field));
		cache.hSet(newKey, FIELD_DECODE_FIELD, field);
		// 设置缓存数据字段
		cache.hSet(newKey, FIELD_DATA, o);
		if (liveTime > 0) {
			cache.expire(newKey, liveTime);
		}
	}

}
