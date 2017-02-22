package com.wpc.admin.service;

import com.wpc.admin.entity.AuthRolePermission;
import com.wpc.common.BaseService;

/**
 * 操作相关
 * author wpc
 */
public interface AuthRolePermissionService extends BaseService<AuthRolePermission, Integer> {
	
	public final static String BEAN_ID="authRolePermissionService";
	
	/**
	 * 保存角色权限关系
	 * @param roleId
	 * @param perIds
	 */
	void saveRolePermissions(int roleId, int[] perIds);
	
}
