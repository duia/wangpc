package com.wpc.admin.service;

import com.wpc.admin.entity.AuthRolePermission;
import com.wpc.common.base.service.BaseService;

/**
 * 操作相关
 * author wpc
 */
public interface AuthRolePermissionService extends BaseService<AuthRolePermission, Long> {
	
	/**
	 * 保存角色权限关系
	 * @param roleId
	 * @param perIds
	 */
	void saveRolePermissions(long roleId, long[] perIds);
	
}
