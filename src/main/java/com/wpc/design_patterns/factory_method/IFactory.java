package com.wpc.design_patterns.factory_method;

/**
 * 工厂接口，也可以是抽象类public abstract class abstractFactory {}
 * 定义生产产品的方法
 * @author admin
 *
 */
public interface IFactory {
	/**
	 * 参数可以是字符串、Class等，或者也可以没有参数
	 * @return
	 */
	public <T extends IProduct> T product(Class<T> c);
//	public IProduct product(String type);
}
