package com.wpc.admin.dao;

import com.wpc.admin.entity.AuthElement;
import com.wpc.common.base.dao.BaseDao;

/**
 * 操作相关
 * author wpc
 */
public interface AuthElementDao extends BaseDao<AuthElement, Long> {
	
	void deleteByMenuId(long menuId);
	
}
