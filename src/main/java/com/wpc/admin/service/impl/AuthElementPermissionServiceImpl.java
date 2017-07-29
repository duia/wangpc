package com.wpc.admin.service.impl;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthElementPermissionDao;
import com.wpc.admin.entity.AuthElementPermission;
import com.wpc.admin.service.AuthElementPermissionService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthElementPermissionServiceImpl extends BaseServiceImpl<AuthElementPermission, Long> implements AuthElementPermissionService {

	Logger logger = LoggerFactory.getLogger(AuthElementPermissionServiceImpl.class);

	@Autowired
	private AuthElementPermissionDao authElementPermissionDao;

}
