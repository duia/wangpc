package com.wpc.design_patterns.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Singleton3 {

	//要产生的对象最大个数
	private static int maxNum = 3;
	//设置了属性便于测试
	private String name;

	private Singleton3(String name) {
		super();
		this.name = name;
	}

	private Singleton3() {
		super();
	}
	
	//提供一个私有的集合，来储存对象
	private static List<Singleton3> list = new ArrayList<Singleton3>();
	
	//静态代码块，加载类的同时，产生相应的对象保存在集合中
	static {
		for (int i = 0; i < maxNum; i++) {
			list.add(new Singleton3("单例"+i));
		}
	}
	
	//提供静态的对外方式，随机获取一个对象
	public static Singleton3 getInstance(){
		Random random = new Random();
		return list.get(random.nextInt(maxNum));
	}
	
	@Override
	public String toString() {
		return "Singleton3 [name=" + name + "]";
	}

	public static void main(String[] args) {
		System.out.println(Singleton3.getInstance());
	}
}

