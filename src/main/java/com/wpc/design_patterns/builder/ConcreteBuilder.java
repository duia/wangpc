package com.wpc.design_patterns.builder;

/**
 * 具体的建造者实现，针对同种产品可以有不通的建造的实现，也可以针对不同的产品分别实现不同的建造者。
 * @author admin
 *
 */
public class ConcreteBuilder extends Builder {

	@Override
	public void buildHead() {
		// TODO Auto-generated method stub
		product.setHead("胖人头");
	}

	@Override
	public void buildBody() {
		// TODO Auto-generated method stub
		product.setBody("胖人身体");
	}

	@Override
	public void buildHand() {
		// TODO Auto-generated method stub
		product.setHand("胖人手");
	}

	@Override
	public void buildFeet() {
		// TODO Auto-generated method stub
		product.setFeet("胖人脚");
	}

}
