package com.wpc.admin.service.impl;

import javax.annotation.Resource;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.AuthFileDao;
import com.wpc.admin.entity.AuthFile;
import com.wpc.admin.service.AuthFileService;

/**
 * 操作相关
 * author wpc
 */
@Service
public class AuthFileServiceImpl extends BaseServiceImpl<AuthFile, Long> implements AuthFileService {

	Logger logger = LoggerFactory.getLogger(AuthFileServiceImpl.class);

	@Autowired
	private AuthFileDao authFileDao;


}
