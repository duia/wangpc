package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthElementPermissionDao;
import com.wpc.admin.entity.AuthElementPermission;
import com.wpc.admin.service.AuthElementPermissionService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(AuthElementPermissionService.BEAN_ID)
public class AuthElementPermissionServiceImpl extends BaseServiceImpl<AuthElementPermission, Integer> implements AuthElementPermissionService {

	Logger logger = LoggerFactory.getLogger(AuthElementPermissionServiceImpl.class);

	@Resource(name=AuthElementPermissionDao.BEAN_ID)
	private AuthElementPermissionDao authElementPermissionDao;


}
