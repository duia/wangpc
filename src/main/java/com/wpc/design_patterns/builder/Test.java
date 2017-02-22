package com.wpc.design_patterns.builder;

public class Test {

	public static void main(String[] args) {
		
		Builder b1 = new ConcreteBuilder();
		Builder b2 = new ConcreteBuilder2();
		
		Director d = new Director(b1);
		System.out.println(d.construct());
		
	}
}
