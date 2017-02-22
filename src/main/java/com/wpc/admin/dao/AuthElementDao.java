package com.wpc.admin.dao;

import com.wpc.admin.entity.AuthElement;
import com.wpc.common.BaseDao;
/**
 * 操作相关
 * author wpc
 */
public interface AuthElementDao extends BaseDao<AuthElement, Integer> {
	
	public final static String BEAN_ID="authElementDao";
	
	void deleteByMenuId(int menuId);
	
}
