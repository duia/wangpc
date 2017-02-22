package com.wpc.admin.dao;

import com.wpc.admin.entity.AuthUserRole;
import com.wpc.common.BaseDao;
/**
 * 操作相关
 * author wpc
 */
public interface AuthUserRoleDao extends BaseDao<AuthUserRole, Integer> {
	
	public final static String BEAN_ID="authUserRoleDao";
	
}
