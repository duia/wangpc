package com.wpc.admin.dao;

import java.util.List;

import com.wpc.admin.entity.AuthMenu;
import com.wpc.common.BaseDao;
/**
 * 操作相关
 * author wpc
 */
public interface AuthMenuDao extends BaseDao<AuthMenu, Integer> {
	
	public final static String BEAN_ID="authMenuDao";
	
	public List<AuthMenu> getLeftMenu();
	
}
