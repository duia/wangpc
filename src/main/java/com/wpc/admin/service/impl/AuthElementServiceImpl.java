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
import com.wpc.admin.service.AuthPermissionService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(AuthElementService.BEAN_ID)
public class AuthElementServiceImpl extends BaseServiceImpl<AuthElement, Integer> implements AuthElementService {

	Logger logger = LoggerFactory.getLogger(AuthElementServiceImpl.class);

	@Resource(name=AuthMenuDao.BEAN_ID)
	private AuthMenuDao authMenuDao;
	@Resource(name=AuthElementDao.BEAN_ID)
	private AuthElementDao authElementDao;
	@Resource(name=AuthPermissionDao.BEAN_ID)
	private AuthPermissionDao authPermissionDao;

	@Resource(name=AuthPermissionService.BEAN_ID)
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
	public List<AuthElement> queryElementByMenuId(int menuId) {
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
	public void delete(Integer id) {
		authPermissionDao.deleteByResourceId(id, AuthPermissionService.PER_TYPE_ELEMENT);
		super.delete(id);
	}
	
}
