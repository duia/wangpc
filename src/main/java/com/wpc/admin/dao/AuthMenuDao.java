package com.wpc.admin.dao;

import java.util.List;

import com.wpc.admin.entity.AuthMenu;
import com.wpc.common.base.dao.BaseDao;

/**
 * 操作相关
 * author wpc
 */
public interface AuthMenuDao extends BaseDao<AuthMenu, Long> {
	
	List<AuthMenu> getLeftMenu();
	
}
