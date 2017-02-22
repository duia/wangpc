package com.wpc.design_patterns.singleton;

/**
 * 实际情况是，单例模式使用内部类来维护单例的实现，JVM内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的。
 * 这样当我们第一次调用getInstance的时候，JVM能够帮我们保证instance只被创建一次，并且会保证把赋值给instance的内存初始化完毕，这样我们就不用担心上面的问题。
 * 同时该方法也只会在第一次调用的时候使用互斥机制，这样就解决了低性能问题。
 * @author admin
 *
 */
public class Singleton2 {

	/* 私有构造方法，防止被实例化 */
	private Singleton2(){
		
	}
	
	private static class SingletonFactory {
		private static Singleton2 instance = new Singleton2();
	}
	
	/* 获取实例 */
	public static Singleton2 getInstance(){
		return SingletonFactory.instance;
	}
	
}
