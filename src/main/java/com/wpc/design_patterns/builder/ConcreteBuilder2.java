package com.wpc.design_patterns.builder;

public class ConcreteBuilder2 extends Builder {

	@Override
	public void buildHead() {
		// TODO Auto-generated method stub
		product.setHead("瘦人头");
	}

	@Override
	public void buildBody() {
		// TODO Auto-generated method stub
		product.setBody("瘦人身体");
	}

	@Override
	public void buildHand() {
		// TODO Auto-generated method stub
		product.setHand("瘦人手");
	}

	@Override
	public void buildFeet() {
		// TODO Auto-generated method stub
		product.setFeet("瘦人脚");
	}

}
