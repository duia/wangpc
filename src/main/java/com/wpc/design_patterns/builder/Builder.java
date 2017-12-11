package com.wpc.design_patterns.builder;

/**
 * 所要建造的产品类
 */
class Product {

	private String paths;

	public String getPaths() {
		return paths;
	}
	public void setPaths(String paths) {
		this.paths = paths;
	}

}

/**
 * 抽象建造者，
 * 1、规定了建造的产品（如果有多个产品，可以适当添加抽象产品）
 * 2、规定了建造每个产品所分步骤，不需要具体的实现过程，实现过程由具体的建造者进行实现
 * 3、规定了建造每个产品时的步骤顺序（此步可以在导演类中进行），并建造完后返回对象
 * 每要新建一个类似的产品种类时，均可继承该类，并实现具体的每个步骤即可，无需改动原有的类，实现了代码的可扩展性
 *
 */
abstract class Builder {

	protected abstract void buildPaths();

	public abstract Product build();
}

/**
 * 具体的建造者实现，针对同种产品可以有不通的建造的实现，也可以针对不同的产品分别实现不同的建造者。
 */
class ConcreteBuilder extends Builder {

	private Product product = new Product();

	@Override
	public void buildPaths() {
		product.setPaths("设置产品属性");
	}

	@Override
	public Product build() {
		return product;
	}

}

/**
 * 导演类，预先持有产品的建造者，为需要不同于默认产品的用户提供不同的组装方式
 * 对建造者的管理,并管理建造步骤
 */
class Director {

	private Builder builder;

	public Director(Builder builder){
		this.builder = builder;
	}

	public Product construct(){
		builder.buildPaths();
		return builder.build();
	}
}

class App {
	public static void main(String[] args) {

		Builder builder = new ConcreteBuilder();

		Director director = new Director(builder);
		Product product = director.construct();

	}
}

