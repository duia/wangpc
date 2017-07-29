package com.wpc.admin.entity;

import com.wpc.common.base.entity.BaseEntity;

/**
*  实体类
* author wpc
*/
public class AuthRolePermission extends BaseEntity {

	/**
	 *
	 */
	private Long roleId;
	/**
	 * 
	 */
	private Long permissionId;
	
	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}
	public Long getRoleId(){
		return this.roleId;
	}
	
	public void setPermissionId(Long permissionId){
		this.permissionId=permissionId;
	}
	public Long getPermissionId(){
		return this.permissionId;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("AuthRolePermission[");
		sb.append(",roleId=");
		sb.append(roleId);
		sb.append(",permissionId=");
		sb.append(permissionId);
		sb.append("]");
		return sb.toString();
	}
}
