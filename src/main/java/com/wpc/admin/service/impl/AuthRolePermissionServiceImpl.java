package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthRolePermissionDao;
import com.wpc.admin.entity.AuthRolePermission;
import com.wpc.admin.service.AuthRolePermissionService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthRolePermissionServiceImpl extends BaseServiceImpl<AuthRolePermission, Long> implements AuthRolePermissionService {

	Logger logger = LoggerFactory.getLogger(AuthRolePermissionServiceImpl.class);

	@Autowired
	private AuthRolePermissionDao authRolePermissionDao;

	@Override
	public void saveRolePermissions(long roleId, long[] perIds) {
		AuthRolePermission rp = null;
		authRolePermissionDao.deleteByRoleId(roleId);
		for (long id : perIds) {
			rp = new AuthRolePermission();
			rp.setRoleId(roleId);
			rp.setPermissionId(id);
			authRolePermissionDao.save(rp);
		}
	}


}
