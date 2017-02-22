package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthFileDao;
import com.wpc.admin.entity.AuthFile;
import com.wpc.admin.service.AuthFileService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(AuthFileService.BEAN_ID)
public class AuthFileServiceImpl extends BaseServiceImpl<AuthFile, Integer> implements AuthFileService {

	Logger logger = LoggerFactory.getLogger(AuthFileServiceImpl.class);

	@Resource(name=AuthFileDao.BEAN_ID)
	private AuthFileDao authFileDao;


}
