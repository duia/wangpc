package com.wpc.design_patterns.builder;

/**
 * 导演类，预先持有产品的建造者，为需要不同于默认产品的用户提供不同的组装方式 
 * 对建造者的管理,并管理建造步骤
 * @author admin
 *
 */
public class Director {

	private Builder builder;
    
	/**
     * 构造子
     */
    public Director(Builder builder){
        this.builder = builder;
    }
	
	public Product construct(){
		builder.buildHead();
		builder.buildBody();
		builder.buildHand();
		builder.buildFeet();
		return builder.getProduct();
	}
}
