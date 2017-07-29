package com.wpc.admin.dao;

import java.util.List;

import com.wpc.admin.entity.AuthRole;
import com.wpc.common.base.dao.BaseDao;

/**
 * 操作相关
 * author wpc
 */
public interface AuthRoleDao extends BaseDao<AuthRole, Long> {
	
	List<AuthRole> queryRoleByUserId(long uid);

}
