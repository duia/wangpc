package com.wpc.admin.entity;

import com.wpc.common.base.entity.BaseEntity;

/**
*  实体类
* author wpc
*/
public class AuthFilePermission extends BaseEntity {

	/**
	 * 
	 */
	private Integer fileId;
	/**
	 * 
	 */
	private Integer permissionId;
	
	public void setFileId(Integer fileId){
		this.fileId=fileId;
	}
	public Integer getFileId(){
		return this.fileId;
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
		sb.append("AuthFilePermission[");
		sb.append(",fileId=");
		sb.append(fileId);
		sb.append(",permissionId=");
		sb.append(permissionId);
		sb.append("]");
		return sb.toString();
	}
}
