package com.wpc.admin.service.impl;

import java.util.List;

import com.wpc.annotation.SysLogAnn;
import com.wpc.common.base.service.impl.BaseServiceImpl;
import com.wpc.enums.OperLevel;
import com.wpc.enums.OperType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthElementDao;
import com.wpc.admin.dao.AuthMenuDao;
import com.wpc.admin.dao.AuthPermissionDao;
import com.wpc.admin.entity.AuthElement;
import com.wpc.admin.entity.AuthMenu;
import com.wpc.admin.service.AuthElementService;
import com.wpc.admin.service.AuthMenuService;
import com.wpc.admin.service.AuthPermissionService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthMenuServiceImpl extends BaseServiceImpl<AuthMenu, Long> implements AuthMenuService {

	Logger logger = LoggerFactory.getLogger(AuthMenuServiceImpl.class);

	@Autowired
	private AuthMenuDao authMenuDao;
	@Autowired
	private AuthElementDao authElementDao;
	@Autowired
	private AuthPermissionDao authPermissionDao;

	@Autowired
	private AuthElementService authElementService;
	@Autowired
	private AuthPermissionService authPermissionService;

	@SysLogAnn(operType = OperType.SYSTEM, operLevel = OperLevel.NORM, describe = "获取菜单")
	@Override
	public List<AuthMenu> getLeftMenu() {
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
	public void delete(Long id) {
		authElementDao.deleteByMenuId(id);
		authPermissionDao.deleteByParentId(id, AuthPermissionService.PER_TYPE_ELEMENT);
		authPermissionDao.deleteByResourceId(id, AuthPermissionService.PER_TYPE_MENU);
		super.delete(id);
	}
	
}
