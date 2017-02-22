package com.wpc.design_patterns.adapter;

/**
 * 适配器模式把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作
 * 
 * 适配器模式的优点
 * 	更好的复用性
 *　		系统需要使用现有的类，而此类的接口不符合系统的需要。那么通过适配器模式就可以让这些功能得到更好的复用。
 *　	更好的扩展性
 *　		在实现适配器功能的时候，可以调用自己开发的功能，从而自然地扩展系统的功能。
 * @author admin
 *
 */

/**
 * 类的适配器模式把适配的类的API转换成为目标类的API
 * 核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口时Targetable，通过Adapter类，将Source的功能扩展到Targetable里,这样就相当于再有实现Targetable同时继承了Source的方法
 * @author admin
 *
 */
public class ClassAdapter {

	public static void main(String[] args) {
		Adapter a = new Adapter();
		a.sampleOperation1();
		a.sampleOperation2();
	}
}

class Source {
	/**
	 * 共用该方法
	 */
	public void sampleOperation1(){
		System.out.println("我是原有的方法,比较有共用性...");
	}
}

interface Targetable {
	/**
     * 这是源类有的方法,利用适配器模式，可以把该接口的其他实现类的该方法直接用源类中的方法，无需再次实现。
     */
    public void sampleOperation1(); 
    /**
     * 这是源类没有的方法，接口定义规则，需要实现的方法
     */
    public void sampleOperation2(); 
}

/**
 * 适配类
 * @author admin
 *
 */
class Adapter extends Source implements Targetable {

	@Override
	public void sampleOperation2() {
		// TODO Auto-generated method stub
		System.out.println("这是接口中其他方法的实现...");
	}
	
}