package com.wpc.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthElementDao;
import com.wpc.admin.dao.AuthMenuDao;
import com.wpc.admin.dao.AuthPermissionDao;
import com.wpc.admin.entity.AuthElement;
import com.wpc.admin.entity.AuthMenu;
import com.wpc.admin.service.AuthElementService;
import com.wpc.admin.service.AuthMenuService;
import com.wpc.admin.service.AuthPermissionService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(AuthMenuService.BEAN_ID)
public class AuthMenuServiceImpl extends BaseServiceImpl<AuthMenu, Integer> implements AuthMenuService {

	Logger logger = LoggerFactory.getLogger(AuthMenuServiceImpl.class);

	@Resource(name=AuthMenuDao.BEAN_ID)
	private AuthMenuDao authMenuDao;
	@Resource(name=AuthElementDao.BEAN_ID)
	private AuthElementDao authElementDao;
	@Resource(name=AuthPermissionDao.BEAN_ID)
	private AuthPermissionDao authPermissionDao;

	@Resource(name=AuthElementService.BEAN_ID)
	private AuthElementService authElementService;
	@Resource(name=AuthPermissionService.BEAN_ID)
	private AuthPermissionService authPermissionService;
	
	@Override
	public List<AuthMenu> getLeftMenu() {
		// TODO Auto-generated method stub
		return authMenuDao.getLeftMenu();
	}

	@Override
	public void save(AuthMenu menu) {
		// TODO Auto-generated method stub
		authMenuDao.save(menu);
		authPermissionService.addMenuPermission(menu);
		//首次保存要关联保存一下四个默认的按钮
		authElementService.addDefaultElements(menu);
	}

	@Override
	public void update(AuthMenu menu) {
		super.update(menu);
		authPermissionService.addMenuPermission(menu);
		//修改时需要关联所有的按钮权限
		for(AuthElement element : authElementService.queryElementByMenuId(menu.getId())){
			authPermissionService.addElementPermission(element);
		}
	}

	@Override
	public void delete(Integer id) {
		authElementDao.deleteByMenuId(id);
		authPermissionDao.deleteByParentId(id, AuthPermissionService.PER_TYPE_ELEMENT);
		authPermissionDao.deleteByResourceId(id, AuthPermissionService.PER_TYPE_MENU);
		super.delete(id);
	}
	
}
