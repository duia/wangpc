package com.wpc.design_patterns.adapter;

/**
 * 对象适配模式
 * @author admin
 *
 */
public class ObjectAdapter {

	public static void main(String[] args) {
		Source1 s = new Source1();
		Adapter1 a = new Adapter1(s);
		a.sampleOperation1();
		a.sampleOperation2();
	}
}

class Source1 {
	/**
	 * 共用该方法
	 */
	public void sampleOperation1(){
		System.out.println("我是原有的方法,比较有共用性...");
	}
}

interface Targetable1 {
	/**
     * 这是源类有的方法,利用适配器模式，可以把该接口的其他实现类的该方法直接用源类中的方法，无需再次实现。
     */
    public void sampleOperation1(); 
    /**
     * 这是源类没有的方法，接口定义规则，需要实现的方法
     */
    public void sampleOperation2(); 
}

class Adapter1 implements Targetable1 {

	private Source1 s;
	
	public Adapter1(Source1 s) {
		super();
		this.s = s;
	}

	@Override
	public void sampleOperation1() {
		// TODO Auto-generated method stub
		s.sampleOperation1();
	}

	@Override
	public void sampleOperation2() {
		// TODO Auto-generated method stub
		System.out.println("这是接口中其他方法的实现...");
	}
	
}