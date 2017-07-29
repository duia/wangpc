package com.wpc.admin.entity;

import com.wpc.common.base.entity.BaseEntity;

/**
*  实体类
* author wpc
*/
public class AuthUserRole extends BaseEntity {

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
		sb.append(",userId=");
		sb.append(userId);
		sb.append(",roleId=");
		sb.append(roleId);
		sb.append("]");
		return sb.toString();
	}
}
