package com.wpc.admin.entity;

import java.io.Serializable;

/**
*  实体类
* author wpc
*/
public class AuthElementPermission implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private Integer elementId;
	/**
	 * 
	 */
	private Integer permissionId;
	
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	
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
		sb.append("id=");
		sb.append(id);
		sb.append(",elementId=");
		sb.append(elementId);
		sb.append(",permissionId=");
		sb.append(permissionId);
		sb.append("]");
		return sb.toString();
	}
}
