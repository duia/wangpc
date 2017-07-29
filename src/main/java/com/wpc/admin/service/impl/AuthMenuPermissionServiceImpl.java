package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthMenuPermissionDao;
import com.wpc.admin.entity.AuthMenuPermission;
import com.wpc.admin.service.AuthMenuPermissionService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthMenuPermissionServiceImpl extends BaseServiceImpl<AuthMenuPermission, Long> implements AuthMenuPermissionService {

	Logger logger = LoggerFactory.getLogger(AuthMenuPermissionServiceImpl.class);

	@Autowired
	private AuthMenuPermissionDao authMenuPermissionDao;


}
