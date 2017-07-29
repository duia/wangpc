package com.wpc.admin.service.impl;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthUserRoleDao;
import com.wpc.admin.entity.AuthUserRole;
import com.wpc.admin.service.AuthUserRoleService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthUserRoleServiceImpl extends BaseServiceImpl<AuthUserRole, Long> implements AuthUserRoleService {

	Logger logger = LoggerFactory.getLogger(AuthUserRoleServiceImpl.class);

	@Autowired
	private AuthUserRoleDao authUserRoleDao;


}
