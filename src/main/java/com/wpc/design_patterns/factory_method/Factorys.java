package com.wpc.design_patterns.factory_method;

import java.util.HashMap;
import java.util.Map;

public class Factorys {

	private static final Map<String, IProduct> map = new HashMap<String, IProduct>();
	
	@SuppressWarnings("unchecked")
	public static synchronized <T extends IProduct> T product(Class<T> c) {
		// TODO Auto-generated method stub
		T t = null;
		if(map.containsKey(c.getName())) {
			return (T) map.get(c.getName());
		}
		try {
			System.out.println("创建产品...");
			t = (T) Class.forName(c.getName()).newInstance();
			map.put(c.getName(), t);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return t;
	}
	
	public static void main(String[] args) {
		IProduct pA = Factorys.product(ProductA.class);
		pA.run();
		IProduct pB = Factorys.product(ProductA.class);
		pB.run();
	}

}
