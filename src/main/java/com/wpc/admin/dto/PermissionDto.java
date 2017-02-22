package com.wpc.admin.dto;

import com.wpc.admin.entity.AuthPermission;

public class PermissionDto extends AuthPermission {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4754031999709999089L;
	
	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
