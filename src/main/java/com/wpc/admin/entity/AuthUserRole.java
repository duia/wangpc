package com.wpc.admin.entity;

import java.io.Serializable;

/**
*  实体类
* author wpc
*/
public class AuthUserRole implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer roleId;
	
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	
	public void setUserId(Integer userId){
		this.userId=userId;
	}
	public Integer getUserId(){
		return this.userId;
	}
	
	public void setRoleId(Integer roleId){
		this.roleId=roleId;
	}
	public Integer getRoleId(){
		return this.roleId;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("AuthUserRole[");
		sb.append("id=");
		sb.append(id);
		sb.append(",userId=");
		sb.append(userId);
		sb.append(",roleId=");
		sb.append(roleId);
		sb.append("]");
		return sb.toString();
	}
}
