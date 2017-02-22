package com.wpc.design_patterns.builder;

/**
 * 抽象建造者，
 * 1、规定了建造的产品
 * 2、规定了建造每个产品所分步骤，不需要具体的实现过程，实现过程由具体的建造者进行实现
 * 3、规定了建造每个产品时的步骤顺序（此步一般在导演类中进行），并建造完后返回对象
 * 每要新建一个类似的产品种类时，均可继承该类，并实现具体的每个步骤即可，无需改动原有的类，实现了代码的可扩展性
 * @author admin
 *
 */
public abstract class Builder {

	protected Product product = new Product();
	
	protected abstract void buildHead();
	protected abstract void buildBody();
	protected abstract void buildHand();
	protected abstract void buildFeet();
	
	public Product getProduct(){
		return product;
	};
}
