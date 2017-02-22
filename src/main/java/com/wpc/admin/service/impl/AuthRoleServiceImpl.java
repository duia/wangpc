package com.wpc.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthRoleDao;
import com.wpc.admin.entity.AuthRole;
import com.wpc.admin.service.AuthRoleService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(AuthRoleService.BEAN_ID)
public class AuthRoleServiceImpl extends BaseServiceImpl<AuthRole, Integer> implements AuthRoleService {

	Logger logger = LoggerFactory.getLogger(AuthRoleServiceImpl.class);

	@Resource(name=AuthRoleDao.BEAN_ID)
	private AuthRoleDao authRoleDao;

}
