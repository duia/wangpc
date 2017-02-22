package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthFilePermissionDao;
import com.wpc.admin.entity.AuthFilePermission;
import com.wpc.admin.service.AuthFilePermissionService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(AuthFilePermissionService.BEAN_ID)
public class AuthFilePermissionServiceImpl extends BaseServiceImpl<AuthFilePermission, Integer> implements AuthFilePermissionService {

	Logger logger = LoggerFactory.getLogger(AuthFilePermissionServiceImpl.class);

	@Resource(name=AuthFilePermissionDao.BEAN_ID)
	private AuthFilePermissionDao authFilePermissionDao;


}
