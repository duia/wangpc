package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthRolePermissionDao;
import com.wpc.admin.entity.AuthRolePermission;
import com.wpc.admin.service.AuthRolePermissionService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(AuthRolePermissionService.BEAN_ID)
public class AuthRolePermissionServiceImpl extends BaseServiceImpl<AuthRolePermission, Integer> implements AuthRolePermissionService {

	Logger logger = LoggerFactory.getLogger(AuthRolePermissionServiceImpl.class);

	@Resource(name=AuthRolePermissionDao.BEAN_ID)
	private AuthRolePermissionDao authRolePermissionDao;

	@Override
	public void saveRolePermissions(int roleId, int[] perIds) {
		AuthRolePermission rp = null;
		authRolePermissionDao.deleteByRoleId(roleId);
		for (int id : perIds) {
			rp = new AuthRolePermission();
			rp.setRoleId(roleId);
			rp.setPermissionId(id);
			authRolePermissionDao.save(rp);
		}
	}


}
