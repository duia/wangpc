package com.wpc.sys.service.impl;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.sys.dao.FilePermissionDao;
import com.wpc.sys.model.FilePermission;
import com.wpc.sys.service.FilePermissionService;

/**
 * 功能描述: 
 * @Author: 王鹏程 
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
@Service
public class FilePermissionServiceImpl extends BaseServiceImpl<FilePermission, Long> implements FilePermissionService {

    @Autowired
    private FilePermissionDao filePermissionDao;
    
}
