package com.wpc.admin.service;

import java.util.List;

import com.wpc.admin.entity.AuthMenu;
import com.wpc.common.BaseService;

/**
 * 操作相关
 * author wpc
 */
public interface AuthMenuService extends BaseService<AuthMenu, Integer> {
	
	public final static String BEAN_ID="menuService";
	
	public List<AuthMenu> getLeftMenu();

}
