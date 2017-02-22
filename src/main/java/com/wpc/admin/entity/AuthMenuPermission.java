package com.wpc.admin.entity;

import java.io.Serializable;

/**
*  实体类
* author wpc
*/
public class AuthMenuPermission implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private Integer menuId;
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
	
	public void setMenuId(Integer menuId){
		this.menuId=menuId;
	}
	public Integer getMenuId(){
		return this.menuId;
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
		sb.append("AuthMenuPermission[");
		sb.append("id=");
		sb.append(id);
		sb.append(",menuId=");
		sb.append(menuId);
		sb.append(",permissionId=");
		sb.append(permissionId);
		sb.append("]");
		return sb.toString();
	}
}
