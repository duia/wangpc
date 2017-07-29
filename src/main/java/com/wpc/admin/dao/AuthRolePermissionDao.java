package com.wpc.admin.dao;

import com.wpc.admin.entity.AuthRolePermission;
import com.wpc.common.base.dao.BaseDao;

/**
 * 操作相关
 * author wpc
 */
public interface AuthRolePermissionDao extends BaseDao<AuthRolePermission, Long> {
	
	/**
	 * 通过角色ID删除数据
	 * @param roleId
	 */
	void deleteByRoleId(long roleId);
	
}
