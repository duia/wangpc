package com.wpc.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
*  实体类
* author wpc
*/
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String account;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private Integer age;
	
	private double price;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 */
	private Date updateTime;
	
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return this.username;
	}
	
	public void setAccount(String account){
		this.account=account;
	}
	public String getAccount(){
		return this.account;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setAge(Integer age){
		this.age=age;
	}
	public Integer getAge(){
		return this.age;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("User[");
		sb.append("id=");
		sb.append(id);
		sb.append(",username=");
		sb.append(username);
		sb.append(",account=");
		sb.append(account);
		sb.append(",password=");
		sb.append(password);
		sb.append(",age=");
		sb.append(age);
		sb.append(",updateTime=");
		sb.append(updateTime);
		sb.append("]");
		return sb.toString();
	}
}
