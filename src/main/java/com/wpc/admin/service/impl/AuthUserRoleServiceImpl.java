package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthUserRoleDao;
import com.wpc.admin.entity.AuthUserRole;
import com.wpc.admin.service.AuthUserRoleService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(AuthUserRoleService.BEAN_ID)
public class AuthUserRoleServiceImpl extends BaseServiceImpl<AuthUserRole, Integer> implements AuthUserRoleService {

	Logger logger = LoggerFactory.getLogger(AuthUserRoleServiceImpl.class);

	@Resource(name=AuthUserRoleDao.BEAN_ID)
	private AuthUserRoleDao authUserRoleDao;


}
