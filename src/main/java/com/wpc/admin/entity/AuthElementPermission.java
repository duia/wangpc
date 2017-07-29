package com.wpc.admin.entity;

import com.wpc.common.base.entity.BaseEntity;

import java.io.Serializable;

/**
*  实体类
* author wpc
*/
public class AuthElementPermission extends BaseEntity {

	/**
	 * 
	 */
	private Integer elementId;
	/**
	 * 
	 */
	private Integer permissionId;
	
	public void setElementId(Integer elementId){
		this.elementId=elementId;
	}
	public Integer getElementId(){
		return this.elementId;
	}
	
	public void setPermissionId(Integer permissionId){
		this.permissionId=permissionId;
	}
	public Integer getPermissionId(){
		return this.permissionId;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("AuthElementPermission[");
		sb.append(",elementId=");
		sb.append(elementId);
		sb.append(",permissionId=");
		sb.append(permissionId);
		sb.append("]");
		return sb.toString();
	}
}
