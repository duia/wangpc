package com.wpc.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.wpc.common.base.service.impl.BaseServiceImpl;
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
import com.wpc.admin.service.AuthPermissionService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthElementServiceImpl extends BaseServiceImpl<AuthElement, Long> implements AuthElementService {

	Logger logger = LoggerFactory.getLogger(AuthElementServiceImpl.class);

	@Autowired
	private AuthMenuDao authMenuDao;
	@Autowired
	private AuthElementDao authElementDao;
	@Autowired
	private AuthPermissionDao authPermissionDao;

	@Autowired
	private AuthPermissionService authPermissionService;

	@Override
	public void addDefaultElements(AuthMenu menu) {
		AuthElement element = null;
		for (int i = 0; i < AuthPermissionServiceImpl.OPERATION_COUNT; i++) {
			element = new AuthElement();
			element.setMenuId(menu.getId());
			element.setElementName(AuthPermissionService.OPERATION_NAMES[i]);
			element.setElementCode(AuthPermissionService.OPERATION_CODES[i]);
			element.setElementDesc(menu.getMenuName()+"_"+AuthPermissionService.OPERATION_NAMES[i]);
			authElementDao.save(element);
			//给element添加权限
			authPermissionService.addElementPermission(element);
		}
	}

	@Override
	public List<AuthElement> queryElementByMenuId(long menuId) {
		// TODO Auto-generated method stub
		AuthElement query = new AuthElement();
		query.setMenuId(menuId);
		return authElementDao.search(query);
	}

	@Override
	public void saveOrUpdate(AuthElement t) {
		if(t.getId()!=null && t.getId()!=0){
			authElementDao.update(t);
		}else{
			authElementDao.save(t);
		}
		authPermissionService.addElementPermission(t);
	}

	@Override
	public void delete(Long id) {
		authPermissionDao.deleteByResourceId(id, AuthPermissionService.PER_TYPE_ELEMENT);
		super.delete(id);
	}
	
}
