package com.wpc.admin.entity;

import java.io.Serializable;

/**
*  实体类
* author wpc
*/
public class AuthFilePermission implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private Integer fileId;
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
		sb.append("id=");
		sb.append(id);
		sb.append(",fileId=");
		sb.append(fileId);
		sb.append(",permissionId=");
		sb.append(permissionId);
		sb.append("]");
		return sb.toString();
	}
}
