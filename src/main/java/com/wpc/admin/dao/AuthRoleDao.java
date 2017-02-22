package com.wpc.admin.dao;

import java.util.List;

import com.wpc.admin.entity.AuthRole;
import com.wpc.common.BaseDao;
/**
 * 操作相关
 * author wpc
 */
public interface AuthRoleDao extends BaseDao<AuthRole, Integer> {
	
	public final static String BEAN_ID="authRoleDao";
	
	public List<AuthRole> queryRoleByUserId(int uid);
}
