package com.wpc.admin.entity;

import com.wpc.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
*  实体类
* author wpc
*/
public class AuthPermission extends BaseEntity {

	/**
	 * 对应资源id
	 */
	private Long resourceId;
	/**
	 * 所属关系id
	 */
	private Long parentId;
	/**
	 * 
	 */
	private String permissionName;
	/**
	 * 
	 */
	private String permissionCode;
	/**
	 * 
	 */
	private String permissionType;
	/**
	 * 
	 */
	private Date updateTime;
	
	public void setResourceId(Long resourceId){
		this.resourceId=resourceId;
	}
	public Long getResourceId(){
		return this.resourceId;
	}
	
	public void setParentId(Long parentId){
		this.parentId=parentId;
	}
	public Long getParentId(){
		return this.parentId;
	}
	
	public void setPermissionName(String permissionName){
		this.permissionName=permissionName;
	}
	public String getPermissionName(){
		return this.permissionName;
	}
	
	public void setPermissionCode(String permissionCode){
		this.permissionCode=permissionCode;
	}
	public String getPermissionCode(){
		return this.permissionCode;
	}
	
	public void setPermissionType(String permissionType){
		this.permissionType=permissionType;
	}
	public String getPermissionType(){
		return this.permissionType;
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
		sb.append("AuthPermission[");
		sb.append(",resourceId=");
		sb.append(resourceId);
		sb.append(",parentId=");
		sb.append(parentId);
		sb.append(",permissionName=");
		sb.append(permissionName);
		sb.append(",permissionCode=");
		sb.append(permissionCode);
		sb.append(",permissionType=");
		sb.append(permissionType);
		sb.append(",updateTime=");
		sb.append(updateTime);
		sb.append("]");
		return sb.toString();
	}
}
