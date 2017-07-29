package com.wpc.admin.service.impl;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthRoleDao;
import com.wpc.admin.entity.AuthRole;
import com.wpc.admin.service.AuthRoleService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthRoleServiceImpl extends BaseServiceImpl<AuthRole, Long> implements AuthRoleService {

	Logger logger = LoggerFactory.getLogger(AuthRoleServiceImpl.class);

	@Autowired
	private AuthRoleDao authRoleDao;

}
