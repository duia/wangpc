package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthFilePermissionDao;
import com.wpc.admin.entity.AuthFilePermission;
import com.wpc.admin.service.AuthFilePermissionService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthFilePermissionServiceImpl extends BaseServiceImpl<AuthFilePermission, Long> implements AuthFilePermissionService {

	Logger logger = LoggerFactory.getLogger(AuthFilePermissionServiceImpl.class);

	@Autowired
	private AuthFilePermissionDao authFilePermissionDao;


}
