package com.wpc.design_patterns.builder;

/**
 * 所要建造的产品类
 * 可以由多个产品类，但需要他们的建造过程一样，每个产品类有其相应的建造者实现
 * @author admin
 *
 */
public class Product {

	private String head;
	private String body;
	private String hand;
	private String feet;
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getHand() {
		return hand;
	}
	public void setHand(String hand) {
		this.hand = hand;
	}
	public String getFeet() {
		return feet;
	}
	public void setFeet(String feet) {
		this.feet = feet;
	}
	@Override
	public String toString() {
		return "Product [head=" + head + ", body=" + body + ", hand=" + hand + ", feet=" + feet + "]";
	}
	
}
