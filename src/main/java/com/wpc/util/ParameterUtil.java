package com.wpc.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ParameterUtil {

	private Properties propertie = new Properties();
	
	{
		try {
			propertie.load(ParameterUtil.class.getResourceAsStream("/config/param.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void autoEvalVal(){
		try {
			Class<?> clazz = Class.forName("com.wpc.util.entity.Parameter");
			for(Field field:clazz.getFields()){
				//获取修饰字段类型
				String fieldType = field.getType().getSimpleName();
				//获取properties文件里的值
				String value = propertie.getProperty(field.getName());
				//根据字段进行赋值 支持list map integer string
				if("List".equals(fieldType)){
					String arrayStr[] = value.split(",");
					ParameterizedType parameterizedType=(ParameterizedType)field.getGenericType();
					//获取泛型类型
					Class<?> parameterizedClass = (Class<?>)parameterizedType.getActualTypeArguments()[0];
					//对比泛型 目前为integer 与 STRING
					if("String".equals(parameterizedClass.getSimpleName())){
						List<String> list = new ArrayList<String>();
						for(String s:arrayStr){
							list.add(s);
						}
						field.set(new ArrayList<String>(), list);
					}else if("Integer".equals(parameterizedClass.getSimpleName())){
						List<Integer> list = new ArrayList<Integer>();
						for(String s:arrayStr){
							list.add(Integer.parseInt(s));
						}
						field.set(new ArrayList<Integer>(), list);
					}
				}else if("Map".equals(fieldType)){
//					//解析json串
//					JSONArray array= (JSONArray)JSONValue.parse(value);
//					Map<String,String> map = new HashMap<String,String>();
//					for(Iterator iterator=array.iterator();iterator.hasNext();){
//						JSONObject jsonObj=(JSONObject)iterator.next();
//						Set<Map.Entry<String,String>> obj=jsonObj.entrySet();
//						String k = null;
//						String v = null;
//						for(Map.Entry<String,String> entry:obj){
//							k=entry.getKey();
//							v=entry.getValue();
//						}
//						map.put(k, v);
//					}
//					field.set(new HashMap<String,String>(), map);
				}else if("String".equals(fieldType)){
					field.set(String.class, value);
				}else if("Integer".equals(fieldType) || "int".equals(fieldType)){
					field.set(Integer.class, Integer.parseInt(value));
				}					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
