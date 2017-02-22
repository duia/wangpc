package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthMenuPermissionDao;
import com.wpc.admin.entity.AuthMenuPermission;
import com.wpc.admin.service.AuthMenuPermissionService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(AuthMenuPermissionService.BEAN_ID)
public class AuthMenuPermissionServiceImpl extends BaseServiceImpl<AuthMenuPermission, Integer> implements AuthMenuPermissionService {

	Logger logger = LoggerFactory.getLogger(AuthMenuPermissionServiceImpl.class);

	@Resource(name=AuthMenuPermissionDao.BEAN_ID)
	private AuthMenuPermissionDao authMenuPermissionDao;


}
