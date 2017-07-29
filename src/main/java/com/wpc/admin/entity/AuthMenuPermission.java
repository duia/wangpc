package com.wpc.admin.entity;

import com.wpc.common.base.entity.BaseEntity;

/**
*  实体类
* author wpc
*/
public class AuthMenuPermission extends BaseEntity {

	/**
	 * 
	 */
	private Integer menuId;
	/**
	 * 
	 */
	private Integer permissionId;
	
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
		sb.append(",menuId=");
		sb.append(menuId);
		sb.append(",permissionId=");
		sb.append(permissionId);
		sb.append("]");
		return sb.toString();
	}
}
