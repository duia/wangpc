package com.wpc.admin.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthMenuDao;
import com.wpc.admin.dao.AuthPermissionDao;
import com.wpc.admin.dto.PermissionDto;
import com.wpc.admin.entity.AuthElement;
import com.wpc.admin.entity.AuthFile;
import com.wpc.admin.entity.AuthMenu;
import com.wpc.admin.entity.AuthPermission;
import com.wpc.admin.service.AuthPermissionService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthPermissionServiceImpl extends BaseServiceImpl<AuthPermission, Long> implements AuthPermissionService {

	Logger logger = LoggerFactory.getLogger(AuthPermissionServiceImpl.class);

	@Autowired
	private AuthMenuDao authMenuDao;
	@Autowired
	private AuthPermissionDao authPermissionDao;
	
	@Override
	public void addElementPermission(AuthElement element) {
		boolean isExist = true;
		AuthMenu menu = authMenuDao.findById(element.getMenuId());
		AuthPermission per = authPermissionDao.findByResourceId(element.getId(), AuthPermissionService.PER_TYPE_ELEMENT);
		if(per == null){
			per = new AuthPermission();
			isExist = false;
		}
		per.setPermissionName(element.getElementName());
		per.setPermissionCode(menu.getMenuCode()+":"+element.getElementCode());
		per.setPermissionType(AuthPermissionService.PER_TYPE_ELEMENT);
		per.setResourceId(element.getId());
		per.setParentId(element.getMenuId());
		per.setUpdateTime(new Date());
		if(isExist){
			authPermissionDao.update(per);
		}else{
			authPermissionDao.save(per);
		}
	}

	@Override
	public void addMenuPermission(AuthMenu menu) {
		boolean isExist = true;
		AuthPermission per = authPermissionDao.findByResourceId(menu.getId(), AuthPermissionService.PER_TYPE_MENU);
		if(per == null){
			per = new AuthPermission();
			isExist = false;
		}
		per.setPermissionName(menu.getMenuName());
		per.setPermissionCode(menu.getMenuCode());
		per.setPermissionType(AuthPermissionService.PER_TYPE_MENU);
		per.setResourceId(menu.getId());
		per.setParentId(0L);
		per.setUpdateTime(new Date());
		if(isExist){
			authPermissionDao.update(per);
		}else{
			authPermissionDao.save(per);
		}
	}

	@Override
	public void addFilePermission(AuthFile file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PermissionDto> getAllPermissionsByRole(long roleId) {
		return authPermissionDao.getAllPermissionsByRole(roleId);
	}
	
}
