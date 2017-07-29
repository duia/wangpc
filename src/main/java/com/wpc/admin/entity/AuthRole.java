package com.wpc.admin.entity;

import com.wpc.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
*  实体类
* author wpc
*/
public class AuthRole extends BaseEntity {

	/**
	 * 
	 */
	private String roleName;
	/**
	 * 
	 */
	private String roleCode;
	/**
	 * 
	 */
	private Date updateTime;
	
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	public String getRoleName(){
		return this.roleName;
	}
	
	public void setRoleCode(String roleCode){
		this.roleCode=roleCode;
	}
	public String getRoleCode(){
		return this.roleCode;
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
		sb.append("AuthRole[");
		sb.append(",roleName=");
		sb.append(roleName);
		sb.append(",roleCode=");
		sb.append(roleCode);
		sb.append(",updateTime=");
		sb.append(updateTime);
		sb.append("]");
		return sb.toString();
	}
}
